package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.EnderecoEntity;

public class EnderecoEntityMock {
    public static EnderecoEntity getEstadoEntity() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setId(1L);
        enderecoEntity.setCep("98765-123");
        enderecoEntity.setLogradouro("Rua Entity Teste");
        enderecoEntity.setLogradouro("Teste");
        enderecoEntity.setBairro("Bairro Entity");
        enderecoEntity.setCidade("Cidade Entity");
        enderecoEntity.setEstado("Estado Entity");
        return enderecoEntity;
    }

}
