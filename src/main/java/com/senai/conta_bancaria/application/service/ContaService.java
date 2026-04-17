package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.DTO.ContaRequestDTO;
import com.senai.conta_bancaria.application.DTO.ContaResponseDTO;
import com.senai.conta_bancaria.application.DTO.UsuarioResponseDTO;
import com.senai.conta_bancaria.domain.Exception.ContaNaoEncontradoException;
import com.senai.conta_bancaria.domain.Exception.UsuarioNaoEncontradoException;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    public ContaResponseDTO cadastrarConta(ContaRequestDTO contaRequestDTO) {
        return ContaResponseDTO.fromEntity(
        contaRepository.save(contaRequestDTO.toEntity())
        );
    }

    public List<ContaResponseDTO> listarUsuarios() {
        return contaRepository.findAll()
                .stream().map(ContaResponseDTO::fromEntity).toList();
    }

    public ContaResponseDTO buscarContaPorId(Long id) {
        return ContaResponseDTO.fromEntity(contaRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradoException(id)));
    }

    public ContaResponseDTO atualizarConta(Long id, @Valid ContaRequestDTO contaRequestDTO) {
        Conta contaAtualizada = contaRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradoException(id));
        contaAtualizada.setAgencia(contaRequestDTO.agencia());
        contaAtualizada.setNumero(contaRequestDTO.numero());
        contaAtualizada.setTipo(contaRequestDTO.tipo());
        contaAtualizada.setSaldo(contaRequestDTO.saldo());
        return ContaResponseDTO.fromEntity(contaRepository.save(contaAtualizada));
    }

    public void deletarConta(Long id) {
        if (!contaRepository.existsById(id)) {
            throw new ContaNaoEncontradoException(id);
        }
        contaRepository.deleteById(id);
    }
}

