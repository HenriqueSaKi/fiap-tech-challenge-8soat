package br.com.fiap.tech_challenge.core.application.usecase;

import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.util.List;

public interface ProdutoUseCase {

    void cadastrarProduto(Produto produto);

    List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto);

    void atualizarProduto(Produto produto);

    void excluirProduto(Long produtoId);
}
