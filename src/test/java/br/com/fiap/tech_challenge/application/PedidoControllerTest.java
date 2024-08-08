package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.adapters.driver.controller.PedidoController;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;
import br.com.fiap.tech_challenge.core.domain.mock.ItemPedidoMock;
import br.com.fiap.tech_challenge.core.domain.ports.in.PedidoServicePort;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Mock private PedidoServicePort service;
    @InjectMocks private PedidoController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();

    }

    @Test
    public void testCadastrarPedido() throws Exception {
        ItemPedidoDTO itemPedidoDTO = ItemPedidoMock.getItemPedido();

        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setItens(List.of(itemPedidoDTO));

        mockMvc.perform(
                        post("/pedido/")
                                .contentType("application/json")
                                .content(new Gson().toJson(pedidoDTO)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testListarPedidosSuccess() throws Exception {
        ItemPedidoDTO itemPedidoDTO = ItemPedidoMock.getItemPedido();
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setItens(List.of(itemPedidoDTO));


        when(service.listarPedidos()).thenReturn(List.of(pedidoDTO));
        mockMvc.perform(get("/pedido/listar"))
                .andExpect(status().isOk());

    }

    @Test
    public void testListarPedidosNotFound() throws Exception {
        when(service.listarPedidos()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/pedido/listar"))
                .andExpect(status().isNotFound());

    }

}
