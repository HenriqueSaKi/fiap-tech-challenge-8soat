package br.com.fiap.tech_challenge.core.application.usecase.impl;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.SituacaoPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.StatusPedidoReponseDTO;
import br.com.fiap.tech_challenge.core.application.exception.cliente.ClienteNaoEncontradoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.ErroAoCadastrarPedidoException;
import br.com.fiap.tech_challenge.core.application.exception.pedido.NenhumPedidoEncontradoException;
import br.com.fiap.tech_challenge.core.application.exception.produto.NenhumProdutoEncontradoException;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ClienteGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.PedidoGatewayPort;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ProdutoGatewayPort;
import br.com.fiap.tech_challenge.core.application.usecase.PedidoUseCase;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static br.com.fiap.tech_challenge.core.application.constant.ClienteExceptionConstante.CLIENTE_NAO_ENCONTRADO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.PedidoExceptionConstante.NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION;
import static br.com.fiap.tech_challenge.core.application.constant.ProdutoExceptionConstante.ERRO_AO_CONSULTAR_PRODUTO_POR_ID_EXCEPTION;

@Service
public class PedidoUseCaseImpl implements PedidoUseCase {

  private final ClienteGatewayPort clienteGatewayPort;
  private final PedidoGatewayPort pedidoGatewayPort;
  private final ProdutoGatewayPort produtoGatewayPort;

  @Autowired
  public PedidoUseCaseImpl(ClienteGatewayPort clienteGatewayPort,
                           PedidoGatewayPort pedidoGatewayPort,
                           ProdutoGatewayPort produtoGatewayPort) {
    this.clienteGatewayPort = clienteGatewayPort;
    this.pedidoGatewayPort = pedidoGatewayPort;
    this.produtoGatewayPort = produtoGatewayPort;
  }

  @Override
  public Long cadastrarPedido(Pedido pedido) {
    try {
      Cliente cliente = clienteGatewayPort.findById(pedido.getClientId());
      if(cliente == null) {
        throw new EntityNotFoundException();
      }

      pedido.getItens().forEach(item -> {
        Produto produto = produtoGatewayPort.findById(item.getIdProduto());
        if (produto == null) {
          throw new NenhumProdutoEncontradoException(
              ERRO_AO_CONSULTAR_PRODUTO_POR_ID_EXCEPTION +
                  item.getIdProduto().toString());
        }

        item.setDescricao(produto.getDescricao());
        item.setValorUnitario(produto.getPreco());
      });

      // Valor mockado por conta do FAKE CHECKOUT
      pedido.setSituacaoPedido(SituacaoPedido.PAGAMENTO_RECEBIDO);
      pedido.setDataPedido(new Date());
      pedido.setValorTotalPedido(getValorTotalPedido(pedido.getItens()));

      return pedidoGatewayPort.cadastrarPedidos(pedido, cliente);

    } catch (EntityNotFoundException e) {
      throw new ClienteNaoEncontradoException(CLIENTE_NAO_ENCONTRADO_EXCEPTION);
    } catch (Exception e) {
      throw new ErroAoCadastrarPedidoException(ERRO_AO_CADASTRAR_PEDIDO_EXCEPTION);
    }

  }

  @Override
  public List<Pedido> listarPedidos() {
    List<Pedido> pedidos = pedidoGatewayPort.listaPedidos();
    if (pedidos.isEmpty()) {
      throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);
    }
    return pedidos;
  }

  @Override
  public StatusPedidoReponseDTO consultaStatusPedido(Long id) {
    Pedido pedido = pedidoGatewayPort.consultaStatusPedidoPorId(id);
    if(pedido != null) {
        SituacaoPedido situacaoPedido = pedido.getSituacaoPedido();
        SituacaoPedidoDTO situacaoPedidoDTO = SituacaoPedidoDTO.valueOf(situacaoPedido.name());
        StatusPedidoReponseDTO responseDTO = new StatusPedidoReponseDTO();
        responseDTO.setSituacaoPedidoDTO(situacaoPedidoDTO);
        return responseDTO;
    }
    throw new NenhumPedidoEncontradoException(NENHUM_PEDIDO_FOI_ENCONTRADO_EXCEPTION);

  }

  private ItemPedidoEntity buildItemPedidoEntity(ProdutoEntity produtoEntity, int quantidade) {
    return ItemPedidoEntity.builder()
        .nome(produtoEntity.getNome())
        .descricao(produtoEntity.getDescricao())
        .valorUnitario(produtoEntity.getPreco())
        .quantidade(quantidade)
        .valorTotalPedido(produtoEntity.getPreco()
            .multiply(BigDecimal.valueOf(quantidade)))
        .build();
  }

  public BigDecimal getValorTotalPedido(List<ItemPedido> itensPedido) {
    return itensPedido.stream()
        .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

}
