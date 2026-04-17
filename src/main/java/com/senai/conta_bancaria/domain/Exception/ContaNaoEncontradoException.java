package com.senai.conta_bancaria.domain.Exception;

public class ContaNaoEncontradoException extends RuntimeException {
    public ContaNaoEncontradoException(Long idConta) {
        super("Conta com o id " + idConta + " não foi encontrado");
    }
}
