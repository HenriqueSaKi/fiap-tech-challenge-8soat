package br.com.fiap.tech_challenge.domain.repository;

import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;

import java.util.Optional;

public interface ClienteRepositoryPort {

    Optional<ClienteEntity> findById(String cpf);
    void save(ClienteEntity cliente);
    void deleteById(String cpf);

}
