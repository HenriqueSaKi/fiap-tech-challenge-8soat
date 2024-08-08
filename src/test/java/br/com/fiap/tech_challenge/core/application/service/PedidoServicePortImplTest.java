package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.core.domain.model.ItemPedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import br.com.fiap.tech_challenge.core.domain.mock.ItemPedidoMock;
import br.com.fiap.tech_challenge.core.application.ports.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock.PedidoEntityMock;
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
public class PedidoServicePortImplTest {

    @Mock private PedidoRepositoryPort repositoryPort;
    @InjectMocks private PedidoServicePortImpl service;

    @BeforeEach
    public void setUp() {
        service = new PedidoServicePortImpl(repositoryPort);
    }

    @Test
    public void testCadastrarPedido() {
        ItemPedidoDTO itemPedidoDTO = ItemPedidoMock.getItemPedido();
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setItens(List.of(itemPedidoDTO));

        service.cadastrarPedido(pedidoDTO);
        verify(repositoryPort, times(1))
                .cadastrarPedidos(any());

    }

    @Test
    public void whenListaPedidosIsNotEmpty_thenReturnPedidos() {
        PedidoEntity pedidoEntity = PedidoEntityMock.getPedidoEntity();

        when(repositoryPort.listaPedidos()).thenReturn(List.of(pedidoEntity));
        List<PedidoDTO> pedidoDTOS = service.listarPedidos();

        assertFalse(pedidoDTOS.isEmpty());
        assertEquals(SituacaoPedido.PAGO, pedidoDTOS.get(0).getSituacaoPedido());
        assertEquals("Item Entity Teste", pedidoDTOS.get(0).getItens().get(0).getDescricao());
        assertEquals(2, pedidoDTOS.get(0).getItens().get(0).getQuantidade());
        assertEquals(new BigDecimal("22.44"), pedidoDTOS.get(0).getItens().get(0).getValorUnitario());
        assertEquals(new BigDecimal("44.88"), pedidoDTOS.get(0).getItens().get(0).getValorTotalItem());

    }

    @Test
    public void whenListaPedidosEmpty_thenReturnEmptyList() {
        when(repositoryPort.listaPedidos()).thenReturn(new ArrayList<>());
        List<PedidoDTO> pedidoDTOS = service.listarPedidos();

        assertTrue(pedidoDTOS.isEmpty());

    }


}