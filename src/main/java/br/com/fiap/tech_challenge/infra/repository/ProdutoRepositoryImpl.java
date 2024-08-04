package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.infra.entity.ProdutoEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepositoryPort {

    private final ProdutoRepository repository;

    public ProdutoRepositoryImpl(@Lazy ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProdutoEntity> buscarPorCategoria(CategoriaProduto categoria) {
        return repository.findByCategoriaProduto(categoria);
    }

}
