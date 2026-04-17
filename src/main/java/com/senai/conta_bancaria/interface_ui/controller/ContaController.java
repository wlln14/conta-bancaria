package com.senai.conta_bancaria.interface_ui.controller;

import com.senai.conta_bancaria.application.DTO.ContaRequestDTO;
import com.senai.conta_bancaria.application.DTO.ContaResponseDTO;
import com.senai.conta_bancaria.application.DTO.UsuarioRequestDTO;
import com.senai.conta_bancaria.application.DTO.UsuarioResponseDTO;
import com.senai.conta_bancaria.application.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> cadastrarConta(@Valid @RequestBody ContaRequestDTO contaRequestDTO) {
        ContaResponseDTO contaCadastrada = contaService.cadastrarConta(contaRequestDTO);
        return ResponseEntity.created(URI.create("/conta/"+contaCadastrada.id())).body(contaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> buscarUsuario() {
        return ResponseEntity.ok(contaService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> buscarContaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contaService.buscarContaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> editarConta(@PathVariable Long id, @Valid @RequestBody ContaRequestDTO contaRequestDTO) {
        return ResponseEntity.ok(contaService.atualizarConta(id, contaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

