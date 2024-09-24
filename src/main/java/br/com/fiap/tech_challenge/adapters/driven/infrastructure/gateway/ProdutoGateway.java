package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ProdutoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.application.mapper.ProdutoMapper;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ProdutoGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProdutoGateway implements ProdutoGatewayPort {

  private final ProdutoRepository repository;
  private final ProdutoMapper produtoMapper;

  @Autowired
  public ProdutoGateway(ProdutoRepository repository, ProdutoMapper produtoMapper) {
    this.repository = repository;
    this.produtoMapper = produtoMapper;
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  @Override
  public void save(Produto produto) {
    ProdutoEntity entity = produtoMapper.toEntity(produto);
    repository.save(entity);
  }

  @Override
  public Produto findById(Long produtoId) {
    Optional<ProdutoEntity> produtoEntity = repository.findById(produtoId);
    return produtoEntity.map(produtoMapper::toDTO).orElse(null);
  }

  @Override
  public List<Produto> findProdutosByCategoria(CategoriaProduto categoriaProduto) {
    List<ProdutoEntity> produtoEntities = repository.findAllByCategoriaProduto(categoriaProduto);
    return produtoEntities.stream()
        .map(produtoMapper::toDTO)
        .collect(Collectors.toList());
  }
}