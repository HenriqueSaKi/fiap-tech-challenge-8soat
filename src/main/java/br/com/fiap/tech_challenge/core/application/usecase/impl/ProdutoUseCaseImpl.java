package br.com.fiap.tech_challenge.core.application.usecase.impl;

import br.com.fiap.tech_challenge.core.application.exception.produto.*;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ProdutoGatewayPort;
import br.com.fiap.tech_challenge.core.application.usecase.ProdutoUseCase;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.fiap.tech_challenge.core.application.constant.ProdutoExceptionConstante.*;

@Service
public class ProdutoUseCaseImpl implements ProdutoUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(ProdutoUseCaseImpl.class);
    private final ProdutoGatewayPort produtoGatewayPort;

    @Autowired
    public ProdutoUseCaseImpl(ProdutoGatewayPort produtoGatewayPort) {
        this.produtoGatewayPort = produtoGatewayPort;
    }

    @Override
    public void cadastrarProduto(Produto produto) {
        try {
            produtoGatewayPort.save(produto);
        } catch (Exception e) {
            LOGGER.error("Erro ao cadastrar produto", e);
            throw new ErroAoCadastrarProdutoException(ERRO_AO_CADASTRAR_PRODUTO_EXCEPTION);
        }
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto) {
        try {
            List<Produto> produtos = produtoGatewayPort.findProdutosByCategoria(categoriaProduto);
            if (produtos.isEmpty()) {
                LOGGER.warn("Nenhum produto encontrado para a categoria {}", categoriaProduto);
                throw new NenhumProdutoEncontradoException(PRODUTO_NAO_ENCONTRADO_EXCEPTION);
            }
            return produtos;
        }
        catch (NenhumProdutoEncontradoException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ErroAoConsultarProdutosPorCategoriaException(ERRO_AO_CONSULTAR_POR_CATEGORIA_EXCEPTION);
        }
    }

    @Override
    public void atualizarProduto(Produto produto) {
        if (produtoGatewayPort.findById(produto.getProdutoId()) == null) {
            LOGGER.warn("Produto com ID {} não encontrado", produto.getProdutoId());
            throw new ProdutoNaoEncontradoException(PRODUTO_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            produtoGatewayPort.save(produto);
        } catch (Exception e) {
            LOGGER.error("Erro ao atualizar produto", e);
            throw new ErroAoAtualizarProdutoException(ERRO_AO_ATUALIZAR_PRODUTO_EXCEPTION);
        }
    }

    @Override
    public void excluirProduto(Long produtoId) {
        if (produtoGatewayPort.findById(produtoId) == null) {
            LOGGER.warn("Produto com ID {} não encontrado", produtoId);
            throw new ProdutoNaoEncontradoException(PRODUTO_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            produtoGatewayPort.deleteById(produtoId);
        } catch (Exception exception) {
            LOGGER.error("Erro ao excluir produto", exception);
            throw new ErroAoExcluirProdutoException(ERRO_AO_EXCLUIR_PRODUTO_EXCEPTION);
        }
    }
}