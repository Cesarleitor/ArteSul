package com.arteSul.pedidosystem.service;

import com.arteSul.pedidosystem.dto.PedidoDTO;
import com.arteSul.pedidosystem.exception.BusinessException;
import com.arteSul.pedidosystem.exception.ResourceNotFoundException;
import com.arteSul.pedidosystem.entity.Pedido;
import com.arteSul.pedidosystem.entity.PedidoItem;
import com.arteSul.pedidosystem.entity.Produto;
import com.arteSul.pedidosystem.repository.PedidoRepository;
import com.arteSul.pedidosystem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    private static final String PRODUTO_SEM_PRECO = "Produto sem preco cadastrado: ";

    // Criar pedido
    public PedidoDTO criarPedido(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setVendedorId(dto.getVendedorId());
        pedido.setDatePedido(dto.getDataPedido() != null ? dto.getDataPedido() : java.time.LocalDateTime.now());

        // Transformar DTOs de itens em entidades
        List<PedidoItem> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + itemDTO.getProdutoId()));
            return criarItemPedido(produto, itemDTO.getQuantidade());
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        pedido.validarItens();

        // Calcula o total
        double total = itens.stream()
                .mapToDouble(PedidoItem::getSubtotal)
                .sum();
        pedido.setTotal(total);

        Pedido salvo = pedidoRepository.save(pedido);
        return toDTO(salvo);
    }

    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PedidoDTO buscarPorId(Long id) {
        return toDTO(buscarEntidadePorId(id));
    }

    public PedidoDTO atualizarPedido(Long id, PedidoDTO dto) {
        Pedido pedido = buscarEntidadePorId(id);
        pedido.setVendedorId(dto.getVendedorId());
        pedido.setDatePedido(dto.getDataPedido() != null ? dto.getDataPedido() : pedido.getDatePedido());

        List<PedidoItem> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: " + itemDTO.getProdutoId()));
            return criarItemPedido(produto, itemDTO.getQuantidade());
        }).collect(Collectors.toList());

        pedido.setItens(itens);
        pedido.validarItens();

        double total = itens.stream()
                .mapToDouble(PedidoItem::getSubtotal)
                .sum();
        pedido.setTotal(total);

        Pedido atualizado = pedidoRepository.save(pedido);
        return toDTO(atualizado);
    }

    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido não encontrado!");
        }
        pedidoRepository.deleteById(id);
    }

    private Pedido buscarEntidadePorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado!"));
    }

    private PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setVendedorId(pedido.getVendedorId());
        dto.setDataPedido(pedido.getDatePedido());
        dto.setTotal(pedido.getTotal());
        dto.setItens(pedido.getItens().stream()
                .map(item -> new com.arteSul.pedidosystem.dto.PedidoItemDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario(),
                        item.getSubtotal()))
                .collect(Collectors.toList()));
        return dto;
    }

    private PedidoItem criarItemPedido(Produto produto, Double quantidade) {
        PedidoItem item = new PedidoItem(produto, quantidade);
        double precoUnitario = getPrecoObrigatorio(produto);
        item.setPrecoUnitario(precoUnitario);
        item.setSubtotal(precoUnitario * item.getQuantidade());
        return item;
    }

    private double getPrecoObrigatorio(Produto produto) {
        if (produto.getPreco() == null) {
            throw new BusinessException(PRODUTO_SEM_PRECO + produto.getId());
        }
        return produto.getPreco();
    }
}
