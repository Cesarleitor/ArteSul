package com.arteSul.pedidosystem.controller;


import com.arteSul.pedidosystem.entity.Usuario;
import com.arteSul.pedidosystem.service.UsuarioService;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // LISTAR TODOS USUARIOS
    @GetMapping
    public List<Usuario> ListarTodos() {
        return usuarioService.ListarTodos();
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //CRIAR USUARIO
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    // DELETAR USUARIO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }
    



}
