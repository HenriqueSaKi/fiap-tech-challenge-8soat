package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;

public class ClienteEntityMock {

    public static ClienteEntity getClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(1L);
        clienteEntity.setCpf("10987654321");
        clienteEntity.setNomeCompleto("Teste Entity");
        clienteEntity.setEmail("teste@entity.com.br");
        return clienteEntity;

    }

}
