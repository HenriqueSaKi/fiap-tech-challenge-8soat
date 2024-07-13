package br.com.fiap.tech_challenge.infra.entity.mock;

import br.com.fiap.tech_challenge.infra.entity.EnderecoEntity;

public class EnderecoEntityMock {
    public static EnderecoEntity getEstadoEntity() {
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        enderecoEntity.setCep("98765-123");
        enderecoEntity.setLogradouro("Rua Entity Teste");
        enderecoEntity.setLogradouro("Teste");
        enderecoEntity.setBairro("Bairro Entity");
        enderecoEntity.setCidade("Cidade Entity");
        enderecoEntity.setEstado("Estado Entity");
        return enderecoEntity;
    }

}
