package br.com.fiap.tech_challenge.infra.entity.mock;

import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;

public class ClienteEntityMock {

    public static ClienteEntity getClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf("10987654321");
        clienteEntity.setNomeCompleto("Teste Entity");
        clienteEntity.setEmail("teste@entity.com.br");
        clienteEntity.setTelefone("+55 12 98765-1234");
        return clienteEntity;

    }

}
