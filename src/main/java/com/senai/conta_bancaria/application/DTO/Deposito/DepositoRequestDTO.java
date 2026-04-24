package com.senai.conta_bancaria.application.DTO.Deposito;

import jakarta.validation.constraints.NotNull;

public record DepositoRequestDTO(
        @NotNull
        Long valorDeposito
) {
}
