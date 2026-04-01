package com.arteSul.pedidosystem.repository;

import com.arteSul.pedidosystem.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
