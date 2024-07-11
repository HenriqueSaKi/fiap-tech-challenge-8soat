package br.com.fiap.tech_challenge.domain.repository;

import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;

import java.util.Optional;

public interface ClienteRepositoryPort {
    Optional<ClienteEntity> findById(Long id);
    Optional<ClienteEntity> findByCpf(String cpf);
    void save(ClienteEntity cliente);
    boolean existsById(Long id);
    void deleteById(Long id);

}
