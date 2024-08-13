package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.core.application.mapper.PedidoMapper;
import br.com.fiap.tech_challenge.core.application.ports.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidoServicePortImplTest {

    @Mock private PedidoRepositoryPort pedidoRepositoryPort;
    @Mock private ProdutoRepositoryPort produtoRepositoryPort;
    @Mock private PedidoMapper pedidoMapper;
    @InjectMocks private PedidoServicePortImpl service;

    @BeforeEach
    public void setUp() {
        service = new PedidoServicePortImpl(pedidoRepositoryPort, produtoRepositoryPort, pedidoMapper);
    }

    @Test
    public void testCadastrarPedido() {
        PedidoEntity entity = PedidoEntityMock.getPedidoEntity();
        PedidoDTO dto = new PedidoDTO();
        dto.getItens().add(ItemPedidoMock.getItemPedido());

        when(pedidoMapper.toEntity(any())).thenReturn(entity);
        service.cadastrarPedido(dto);

        verify(pedidoRepositoryPort, times(1))
                .cadastrarPedidos(any());

    }

    @Test
    public void whenListaPedidosIsNotEmpty_thenReturnPedidos() {
        PedidoEntity pedidoEntity = PedidoEntityMock.getPedidoEntity();

        when(pedidoRepositoryPort.listaPedidos()).thenReturn(List.of(pedidoEntity));
        List<PedidoDTO> pedidoDTOS = service.listarPedidos();

        verify(pedidoRepositoryPort, times(1))
                .listaPedidos();
        verify(pedidoMapper, times(1))
                .toDTO(any());

    }

    @Test
    public void whenListaPedidosEmpty_thenReturnEmptyList() {
        when(pedidoRepositoryPort.listaPedidos()).thenReturn(new ArrayList<>());
        List<PedidoDTO> pedidoDTOS = service.listarPedidos();

        assertTrue(pedidoDTOS.isEmpty());

    }


}
