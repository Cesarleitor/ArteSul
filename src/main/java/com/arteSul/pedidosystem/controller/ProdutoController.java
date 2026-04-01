package com.arteSul.pedidosystem.controller;


import com.arteSul.pedidosystem.dto.ProdutoDTO;
import com.arteSul.pedidosystem.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor

public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ProdutoDTO salvar(@RequestBody @Valid ProdutoDTO dto) {
        ProdutoDTO produto = produtoService.salvar(dto);
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getTamanho(), produto.getPreco());
    }

    @GetMapping
    public List<ProdutoDTO> listar() {
        return produtoService.listar()
                .stream()
                .map(p -> new ProdutoDTO(p.getId(), p.getNome(), p.getTamanho(), p.getPreco()))
                .toList();
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizar(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoDTO atualizado = produtoService.atualizar(id, dto);
        return new ProdutoDTO(atualizado.getId(), atualizado.getNome(), atualizado.getTamanho(), atualizado.getPreco());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
