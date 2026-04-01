package com.arteSul.pedidosystem.service;

import com.arteSul.pedidosystem.dto.PedidoDTO;
import com.arteSul.pedidosystem.dto.PedidoItemDTO;
import com.arteSul.pedidosystem.entity.Pedido;
import com.arteSul.pedidosystem.entity.PedidoItem;
import com.arteSul.pedidosystem.entity.Produto;
import com.arteSul.pedidosystem.repository.PedidoRepository;
import com.arteSul.pedidosystem.repository.ProdutoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    // Criar pedido
    public Pedido criarPedido(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setVendedorId(dto.getVendedorId());
        pedido.setDataPedido(dto.getDataPedido() != null ? dto.getDataPedido() : java.time.LocalDateTime.now());

        // Transformar DTOs de itens em entidades
        List<PedidoItem> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));
            return new PedidoItem(produto, itemDTO.getQuantidade());
        }).collect(Collectors.toList());

        pedido.setItens(itens);

        // Calcula o total
        double total = itens.stream()
                .mapToDouble(i -> {
                    try {
                        return Double.parseDouble(i.getProduto().getTamanho()) * i.getQuantidade(); // aqui "tamanho" como exemplo de preço
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .sum();
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));
    }

    public Pedido atualizarPedido(Long id, PedidoDTO dto) {
        Pedido pedido = buscarPorId(id);
        pedido.setVendedorId(dto.getVendedorId());

        List<PedidoItem> itens = dto.getItens().stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.getProdutoId()));
            return new PedidoItem(produto, itemDTO.getQuantidade());
        }).collect(Collectors.toList());

        pedido.setItens(itens);

        double total = itens.stream()
                .mapToDouble(i -> {
                    try {
                        return Double.parseDouble(i.getProduto().getTamanho()) * i.getQuantidade();
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .sum();
        pedido.setTotal(total);

        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado!");
        }
        pedidoRepository.deleteById(id);
    }
}