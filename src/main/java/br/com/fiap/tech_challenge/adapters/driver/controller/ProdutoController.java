package br.com.fiap.tech_challenge.adapters.driver.controller;


import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.ProdutoDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarProdutoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.swagger.ProdutoSwaggerInterface;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.core.application.usecase.ProdutoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoSwaggerInterface {

    private final ProdutoUseCase produtoUseCase;
    private final ProdutoDTOMapper produtoDTOMapper;

    @Autowired
    public ProdutoController(ProdutoUseCase service, ProdutoDTOMapper produtoDTOMapper) {
        this.produtoUseCase = service;
        this.produtoDTOMapper = produtoDTOMapper;
    }

    @Override
    public ResponseEntity<String> cadastrarProduto(CadastrarProdutoDTO cadastrar) {
        Produto produto = produtoDTOMapper.cadastrarToProdutoDTO(cadastrar);
        produtoUseCase.cadastrarProduto(produto);
        return new ResponseEntity<>("Produto cadastrado com sucesso!", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> consultaPorCategoria(CategoriaProduto categoriaProduto) {
        List<Produto> produtosPorCategoria = produtoUseCase.buscarProdutosPorCategoria(categoriaProduto);
        return new ResponseEntity<>(produtosPorCategoria, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> atualizaInformacoesProduto(AtualizarProdutoDTO atualizar) {
        Produto produto = produtoDTOMapper.atualizarToProdutoDTO(atualizar);
        produtoUseCase.atualizarProduto(produto);
        return new ResponseEntity<>("Produto atualizado com sucesso!", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<String> excluirProduto(Long id) {
        produtoUseCase.excluirProduto(id);
        return new ResponseEntity<>("Produto excluido com sucesso!", HttpStatus.OK);
    }
}
