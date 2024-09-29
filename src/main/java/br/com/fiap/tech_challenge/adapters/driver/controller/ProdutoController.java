package br.com.fiap.tech_challenge.adapters.driver.controller;


import br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway.ProdutoGateway;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ProdutoRepository;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.ProdutoSwaggerInterface;
import br.com.fiap.tech_challenge.core.application.usecase.impl.ProdutoUseCaseImpl;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoSwaggerInterface {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public ResponseEntity<String> cadastrarProduto(CadastrarProdutoDTO cadastrar) {
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var produtoUseCase = new ProdutoUseCaseImpl(produtoGateway);

        produtoUseCase.cadastrarProduto(cadastrar);
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> consultaPorCategoria(CategoriaProduto categoriaProduto) {
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var produtoUseCase = new ProdutoUseCaseImpl(produtoGateway);

        List<Produto> produtosPorCategoria = produtoUseCase.buscarProdutosPorCategoria(categoriaProduto);
        return new ResponseEntity<>(produtosPorCategoria, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> atualizaInformacoesProduto(AtualizarProdutoDTO atualizar) {
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var produtoUseCase = new ProdutoUseCaseImpl(produtoGateway);

        produtoUseCase.atualizarProduto(atualizar);
        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> excluirProduto(Long id) {
        var produtoGateway = new ProdutoGateway(this.produtoRepository);
        var produtoUseCase = new ProdutoUseCaseImpl(produtoGateway);

        produtoUseCase.excluirProduto(id);
        return new ResponseEntity<>("Produto excluido com sucesso!", HttpStatus.OK);
    }
}
