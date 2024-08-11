package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.ProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.util.List;
import java.util.Optional;

public interface ProdutoServicePort {

    void cadastrarProduto(ProdutoDTO produtoDTO);

    Optional<ProdutoEntity> buscarProdutoPorId(Integer produtoId);

    List<ProdutoDTO> buscarTodosProdutos();

    List<ProdutoDTO> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto);

    void atualizarProduto(ProdutoDTO produtoDTO);

    boolean existeProduto(Integer produtoId);

    void excluirProduto(Integer produtoId);
}
