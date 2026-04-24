package com.senai.conta_bancaria.domain.entity;

import com.senai.conta_bancaria.domain.enums.Role;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

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

        public void depositar(Long valorDeposito) {
            if (valorDeposito > 0) {
                this.saldo += valorDeposito;
            }
        }

        public boolean saque(Long valorSaque) {
            if (valorSaque > 0 && valorSaque <= saldo) {
                this.saldo -= valorSaque;
                return true;
            }
            return false;
        }

        public void transferencia(Long valorSaque, Conta contaDestino) {
                if (saque(valorSaque)){
                    contaDestino.depositar(valorSaque);
                } else {
                    System.out.println("Nãsokosidh");

                }
        }
    }

