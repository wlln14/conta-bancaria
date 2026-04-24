package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.DTO.Conta.ContaRequestDTO;
import com.senai.conta_bancaria.application.DTO.Conta.ContaResponseDTO;
import com.senai.conta_bancaria.application.DTO.Deposito.DepositoRequestDTO;
import com.senai.conta_bancaria.application.DTO.Saque.SaqueRequestDTO;
import com.senai.conta_bancaria.application.DTO.Transferencia.TransferenciaRequestDTO;
import com.senai.conta_bancaria.domain.Exception.ContaNaoEncontradoException;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
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

    public Conta depositarConta(Long id, DepositoRequestDTO depositoRequestDTO) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradoException(id));

        conta.depositar(depositoRequestDTO.valorDeposito());
        return contaRepository.save(conta);
    }

    public Conta saqueConta(Long id, SaqueRequestDTO saqueRequestDTO) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradoException(id));

        conta.saque(saqueRequestDTO.valorSaque());
        return contaRepository.save(conta);
    }

    public ContaResponseDTO transferenciaConta(TransferenciaRequestDTO transferenciaRequestDTO) {
        Conta contaDestino = contaRepository.findByNumero(transferenciaRequestDTO.contaDestino()).get();
        Conta contaOrigem = contaRepository.findByNumero(transferenciaRequestDTO.contaOrigem()).get();

        contaOrigem.transferencia(transferenciaRequestDTO.valorSaque(), contaDestino);
        contaRepository.save(contaDestino);

        return ContaResponseDTO.fromEntity(contaRepository.save(contaOrigem));
    }
}

