package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;


    public Usuario cadastrarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);


        return usuario;
    }

    public List<Usuario> listarUsuarios() {

        return usuarioRepository.findAll();
    }

    public Usuario buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioAtualizado = buscarUsuarioPorId(id);
        if (usuarioAtualizado != null) {
            usuarioAtualizado.setNome(usuario.getNome());
            usuarioAtualizado.setEmail(usuario.getEmail());
            usuarioAtualizado.setSenha(usuario.getSenha());
            return usuarioRepository.save(usuarioAtualizado);
        }

        return null;
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
