package br.com.fiap.tech_challenge.core.domain.ports.in;

import br.com.fiap.tech_challenge.core.domain.model.ProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;

import java.util.List;
import java.util.Optional;

public interface ProdutoServicePort {

    void cadastrarProduto(ProdutoDTO produtoDTO);

    Optional<ProdutoDTO> buscarPorNome(String nomeProduto);

    List<ProdutoDTO> buscarTodosProdutos();

    List<ProdutoDTO> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto);

    void atualizarProduto(ProdutoDTO produtoDTO);

    void excluirProduto(Long produtoId);
}
