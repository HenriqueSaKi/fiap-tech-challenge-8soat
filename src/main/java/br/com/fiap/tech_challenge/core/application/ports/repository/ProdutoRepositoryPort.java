package br.com.fiap.tech_challenge.core.application.ports.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositoryPort {
    void delete(ProdutoEntity produto);

    ProdutoEntity save(ProdutoEntity produto);

    Optional<ProdutoEntity> findById(Integer produtoId);

    List<ProdutoEntity> findAll();

    Optional<ProdutoEntity> findProdutosByCategoria(CategoriaProduto categoriaProduto);
}
