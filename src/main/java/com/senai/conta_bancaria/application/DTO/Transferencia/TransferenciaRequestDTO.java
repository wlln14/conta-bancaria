package com.senai.conta_bancaria.application.DTO.Transferencia;

import com.senai.conta_bancaria.domain.entity.Conta;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TransferenciaRequestDTO(
        @NotNull
        Long valorSaque,

        @NotEmpty
        String contaDestino,

        @NotEmpty
        String contaOrigem
) {
}
