package com.senai.conta_bancaria.application.DTO.Saque;

import jakarta.validation.constraints.NotNull;

public record SaqueRequestDTO(
        @NotNull
        Long valorSaque
) {
}
