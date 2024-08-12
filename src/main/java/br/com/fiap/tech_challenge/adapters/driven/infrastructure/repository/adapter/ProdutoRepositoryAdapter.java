package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ProdutoRepository;
import br.com.fiap.tech_challenge.core.application.ports.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProdutoRepositoryAdapter implements ProdutoRepositoryPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public void save(ProdutoEntity produto) {
        produtoRepository.save(produto);
    }

    @Override
    public Optional<ProdutoEntity> buscarPorNome(String nomeProduto) {
        return produtoRepository.buscarPorNome(nomeProduto);
    }

    @Override
    public Optional<ProdutoEntity> findById(Long produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @Override
    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public List<ProdutoEntity> findProdutosByCategoria(CategoriaProduto categoriaProduto) {
        return produtoRepository.findProdutosByCategoria(categoriaProduto);
    }
}
