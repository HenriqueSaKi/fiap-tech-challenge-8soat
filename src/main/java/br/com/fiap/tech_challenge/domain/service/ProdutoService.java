package br.com.fiap.tech_challenge.domain.service;

import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.infra.entity.ProdutoEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoService {
//    void delete(ProdutoEntity produto);
//
//    ProdutoEntity save(ProdutoEntity produto);
//
//    Optional<ProdutoEntity> findById(UUID produtoId);
//
//    List<ProdutoEntity> findAll();

    List<ProdutoEntity> buscarPorCategoria(CategoriaProduto categoria);
}


