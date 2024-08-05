package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.domain.repository.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
}
