package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}
