package br.com.fiap.tech_challenge.core.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.Endereco;

public class EnderecoMock {

    public static Endereco getEndereco() {
        Endereco endereco = new Endereco();
        endereco.setCep("12345-123");
        endereco.setLogradouro("Rua Endereco Teste");
        endereco.setLogradouro("Teste");
        endereco.setBairro("Bairro Teste");
        endereco.setCidade("Cidade Teste");
        endereco.setEstado("Estado Teste");
        return endereco;
    }

}
