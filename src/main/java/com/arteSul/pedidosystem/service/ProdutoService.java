package com.arteSul.pedidosystem.service;


import com.arteSul.pedidosystem.dto.ProdutoDTO;
import com.arteSul.pedidosystem.entity.Produto;
import com.arteSul.pedidosystem.exception.ResourceNotFoundException;
import com.arteSul.pedidosystem.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class ProdutoService {

        private final ProdutoRepository produtoRepository;

        // CREATE
        public ProdutoDTO salvar(ProdutoDTO dto) {
            Produto produto = new Produto();
            produto.setNome(dto.getNome());
            produto.setTamanho(dto.getTamanho());
            produto.setPreco(dto.getPreco());
            Produto salvo = produtoRepository.save(produto);
            dto.setId(salvo.getId());
            return dto;
        }

        // LIST
        public List<ProdutoDTO> listar() {
            return produtoRepository.findAll()
                    .stream()
                    .map(p -> {
                        ProdutoDTO dto = new ProdutoDTO();
                        dto.setId(p.getId());
                        dto.setNome(p.getNome());
                        dto.setTamanho(p.getTamanho());
                        dto.setPreco(p.getPreco());
                        return dto;
                    })
                    .collect(Collectors.toList());
        }

        // DELETE
        public void deletar(Long id) {
            if (!produtoRepository.existsById(id)) {
                throw new ResourceNotFoundException("Produto não encontrado!");
            }
            produtoRepository.deleteById(id);
        }

        // BUSCAR POR ID
        public ProdutoDTO buscarPorId(Long id) {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
            ProdutoDTO dto = new ProdutoDTO();
            dto.setId(produto.getId());
            dto.setNome(produto.getNome());
            dto.setTamanho(produto.getTamanho());
            dto.setPreco(produto.getPreco());
            return dto;
        }

        // UPDATE
        public ProdutoDTO atualizar(Long id, ProdutoDTO dtoAtualizado) {
            Produto produto = produtoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));

            produto.setNome(dtoAtualizado.getNome());
            produto.setTamanho(dtoAtualizado.getTamanho());
            produto.setPreco(dtoAtualizado.getPreco());

            produtoRepository.save(produto);

            dtoAtualizado.setId(produto.getId());
            return dtoAtualizado;
        }
    }



