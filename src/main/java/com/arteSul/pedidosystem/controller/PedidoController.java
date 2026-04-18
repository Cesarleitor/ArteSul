package com.arteSul.pedidosystem.controller;


import com.arteSul.pedidosystem.dto.PedidoDTO;
import com.arteSul.pedidosystem.dto.ProdutoDTO;
import com.arteSul.pedidosystem.service.PedidoService;
import com.arteSul.pedidosystem.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    @PostMapping
    public PedidoDTO criar(@RequestBody @Valid PedidoDTO dto) {
        return pedidoService.criarPedido(dto);
    }

    @GetMapping
    public List<PedidoDTO> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public PedidoDTO buscar(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public PedidoDTO atualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        return pedidoService.atualizarPedido(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/lote")
    public List<ProdutoDTO> salvarLote(@RequestBody List<ProdutoDTO> lista) {
        return lista.stream()
                .map(produtoService::salvar)
                .toList();
    }


}