package br.com.fiap.tech_challenge.core.application.exception.produto;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}