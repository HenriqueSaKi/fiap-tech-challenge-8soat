package br.com.fiap.tech_challenge.domain.repository;

import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;

import java.util.Optional;

public interface ClienteRepositoryPort {

    Optional<ClienteEntity> findByCpf(String cpf);
    void save(ClienteEntity cliente);
    void deleteByCpf(String cpf);

}
