package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.DTO.UsuarioRequestDTO;
import com.senai.conta_bancaria.application.DTO.UsuarioResponseDTO;
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


    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        return UsuarioResponseDTO.fromEntity(
                usuarioRepository.save(usuarioRequestDTO.toEntity()
                )
        );
    }

    public List<UsuarioResponseDTO> listarUsuarios() {

        return usuarioRepository.findAll()
                .stream().map(UsuarioResponseDTO::fromEntity).toList();
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        return UsuarioResponseDTO.fromEntity(usuarioRepository.findById(id).get());
    }

    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuarioAtualizado = usuarioRepository.findById(id).get();
        usuarioAtualizado.setNome(usuarioRequestDTO.nome());
        usuarioAtualizado.setEmail(usuarioRequestDTO.email());
        usuarioAtualizado.setSenha(usuarioRequestDTO.senha());
        return UsuarioResponseDTO.fromEntity(usuarioRepository.save(usuarioAtualizado));

    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
