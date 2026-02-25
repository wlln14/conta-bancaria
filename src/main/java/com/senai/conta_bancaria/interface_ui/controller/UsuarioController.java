package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.service.UsuarioService;
import com.senai.conta_bancaria.domain.entity.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    UsuarioService usuarioService;

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody Usuario usuario){
        // @RequestBody fala para o @PostMapping que a informação de usuario está no body do http

        usuarioService.cadastrarUsuario(usuario);
        // Service é responsavel por "orquestrar"

        return usuario;
    }


}
