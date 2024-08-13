package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.math.BigDecimal;

public class ProdutoEntityMock {

  public static ProdutoEntity getProdutoEntity() {
    ProdutoEntity entity = new ProdutoEntity();
    entity.setProdutoId(1L);
    entity.setNome("X-Tudo");
    entity.setDescricao("Lanche com hambúrguer, tomate, cebola e alface");
    entity.setCategoriaProduto(CategoriaProduto.LANCHE);
    entity.setPreco(BigDecimal.valueOf(15.99));
    return entity;

  }

}
