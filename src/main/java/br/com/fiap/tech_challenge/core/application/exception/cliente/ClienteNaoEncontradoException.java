package br.com.fiap.tech_challenge.core.application.exception.cliente;

public class ClienteNaoEncontradoException extends RuntimeException{
    public ClienteNaoEncontradoException(String message) {
        super(message);
    }
}