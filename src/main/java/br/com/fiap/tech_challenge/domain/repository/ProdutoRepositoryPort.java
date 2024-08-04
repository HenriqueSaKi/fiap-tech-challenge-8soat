package br.com.fiap.tech_challenge.domain.repository;

import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.infra.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepositoryPort {
    List<ProdutoEntity> buscarPorCategoria(CategoriaProduto categoria);
}
