package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.service.UsuarioService;
import com.senai.conta_bancaria.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        // @RequestBody fala para o @PostMapping que a informação de usuario está no body do http
        // Service é responsavel por "orquestrar"
        return usuarioService.cadastrarUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> buscarUsuario() {
        return usuarioService.listarUsuarios();
    }
}