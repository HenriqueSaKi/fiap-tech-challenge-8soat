package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.ItemPedido;
import br.com.fiap.tech_challenge.domain.Pedido;
import br.com.fiap.tech_challenge.domain.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.domain.mock.ItemPedidoMock;
import br.com.fiap.tech_challenge.domain.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.infra.entity.PedidoEntity;
import br.com.fiap.tech_challenge.infra.entity.mock.PedidoEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {

    @Mock private PedidoRepositoryPort repositoryPort;
    @InjectMocks private PedidoServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new PedidoServiceImpl(repositoryPort);
    }

    @Test
    public void testCadastrarPedido() {
        ItemPedido itemPedido = ItemPedidoMock.getItemPedido();
        Pedido pedido = new Pedido();
        pedido.setItens(List.of(itemPedido));

        service.cadastrarPedido(pedido);
        verify(repositoryPort, times(1))
                .cadastrarPedidos(any());

    }

    @Test
    public void whenListaPedidosIsNotEmpty_thenReturnPedidos() {
        PedidoEntity pedidoEntity = PedidoEntityMock.getPedidoEntity();

        when(repositoryPort.listaPedidos()).thenReturn(List.of(pedidoEntity));
        List<Pedido> pedidos = service.listarPedidos();

        assertFalse(pedidos.isEmpty());
        assertEquals(SituacaoPedido.RECEBIDO, pedidos.get(0).getSituacaoPedido());
        assertEquals("Item Entity Teste", pedidos.get(0).getItens().get(0).getDescricao());
        assertEquals(2, pedidos.get(0).getItens().get(0).getQuantidade());
        assertEquals(new BigDecimal("22.44"), pedidos.get(0).getItens().get(0).getValorUnitario());
        assertEquals(new BigDecimal("44.88"), pedidos.get(0).getItens().get(0).getValorTotalItem());

    }

    @Test
    public void whenListaPedidosEmpty_thenReturnEmptyList() {
        when(repositoryPort.listaPedidos()).thenReturn(new ArrayList<>());
        List<Pedido> pedidos = service.listarPedidos();

        assertTrue(pedidos.isEmpty());

    }


}
