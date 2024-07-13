package br.com.fiap.tech_challenge.domain.mock;

import br.com.fiap.tech_challenge.domain.Cliente;

public class ClienteMock {

    public static Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678910");
        cliente.setNomeCompleto("Teste Teste");
        cliente.setEmail("teste@exemplo.com.br");
        cliente.setTelefone("+55 12 91234-1234");
        return cliente;

    }

}
