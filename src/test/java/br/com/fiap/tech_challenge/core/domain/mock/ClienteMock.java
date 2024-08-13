package br.com.fiap.tech_challenge.core.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.Cliente;

public class ClienteMock {

    public static Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678910");
        cliente.setNomeCompleto("Teste Teste");
        cliente.setEmail("teste@exemplo.com.br");
        return cliente;

    }

}
