package br.com.fiap.tech_challenge.domain.service.impl;

import br.com.fiap.tech_challenge.domain.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.portas.interfaces.ProdutoServicePort;
import br.com.fiap.tech_challenge.infra.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
//fixme - Eliminar o produtoModel, colocar o DTO direto como parâmetro de entrada e converter dentro do service
//fixme - Aplicar Transactional Annotation nos métodos de negócio. Colocar readOnly onde é somente leitura
public class ProdutoServiceImpl implements ProdutoServicePort {

    private final ProdutoRepository produtoRepository;

    @Override
    public void delete(ProdutoEntity produto) {
        // converter produtomodel para produtoentity
        produtoRepository.delete(produto);
    }

    @Override
    public ProdutoEntity save(ProdutoEntity produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<ProdutoEntity> findById(UUID produtoId) {
        return produtoRepository.findById(produtoId);
    }

    @Override
    public List<ProdutoEntity> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public List<ProdutoEntity> buscarPorCategoria(CategoriaProduto categoria) {
        return produtoRepository.buscarPorCategoria(categoria);
    }
}
