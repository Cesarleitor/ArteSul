package com.arteSul.pedidosystem.controller;


import com.arteSul.pedidosystem.dto.PedidoDTO;
import com.arteSul.pedidosystem.entity.Pedido;
import com.arteSul.pedidosystem.service.PedidoService;
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

    @PostMapping
    public Pedido criar(@RequestBody @Valid PedidoDTO dto) {
        return pedidoService.criarPedido(dto);

    }

    @GetMapping
    private List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public void buscar(@PathVariable Long id) {
        return;

    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        return pedidoService.atualizarPedido(id, dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }


}
