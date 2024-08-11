package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.application.ports.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.ProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.core.domain.ports.in.ProdutoServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoServicePortImpl implements ProdutoServicePort {

    private ProdutoRepositoryPort produtoRepositoryPort;


    @Override
    public void cadastrarProduto(ProdutoDTO produtoDTO) {
    }

    @Override
    public Optional<ProdutoEntity> buscarProdutoPorId(Integer produtoId) {
        return produtoRepositoryPort.findById(produtoId);
    }

    @Override
    public List<ProdutoDTO> buscarTodosProdutos() {
        return List.of();
    }

    @Override
    public List<ProdutoDTO> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto) {
        return List.of();
    }

    @Override
    public void atualizarProduto(ProdutoDTO produtoDTO) {

    }

    @Override
    public boolean existeProduto(Integer produtoId) {
        return false;
    }

    @Override
    public void excluirProduto(Integer produtoId) {

    }
}