package com.senai.conta_bancaria.domain.entity;

import com.senai.conta_bancaria.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class Conta {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String agencia;
        private String numero;
        private String tipo;
        private Long saldo;
        private boolean ativo = true;

        @ManyToOne
        @JoinColumn(name = "usuario_id")
        private Usuario usuario;

    }

