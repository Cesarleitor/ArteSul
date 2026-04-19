package com.arteSul.pedidosystem.repository;

import com.arteSul.pedidosystem.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
