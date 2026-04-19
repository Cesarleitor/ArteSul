package com.arteSul.pedidosystem.service;


import com.arteSul.pedidosystem.entity.Usuario;
import com.arteSul.pedidosystem.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    // LISTAR TODOS OS USUARIOS
    public List<Usuario> ListarTodos() {
        return usuarioRepository.findAll();
    }

    //BUSCAR POR ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // SALVAR USUARIO
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //DELETAR USUARIO
    public void delete(long id) {
        usuarioRepository.deleteById(id);
    }


}
