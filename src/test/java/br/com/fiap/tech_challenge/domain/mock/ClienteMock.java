package br.com.fiap.tech_challenge.domain.mock;

import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;

public class ClienteMock {

    public static ClienteDTO getCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf("12345678910");
        clienteDTO.setNomeCompleto("Teste Teste");
        clienteDTO.setEmail("teste@exemplo.com.br");
        return clienteDTO;

    }

}
