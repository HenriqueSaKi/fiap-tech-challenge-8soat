package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driver.controller.PedidoController;
import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.PedidoDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.ItemPedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
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
    @Mock private PedidoDTOMapper mapper;
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
        ItemPedidoDTO itemPedido = new ItemPedidoDTO();
        itemPedido.setIdProduto(1L);
        itemPedido.setQuantidade(3);

        CadastrarPedidoDTO cadastrarPedidoDTO = new CadastrarPedidoDTO();
        cadastrarPedidoDTO.setItens(new ArrayList<>());
        cadastrarPedidoDTO.getItens().add(itemPedido);

        mockMvc.perform(
                        post("/pedido/")
                                .contentType("application/json")
                                .content(new Gson().toJson(cadastrarPedidoDTO)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testListarPedidosSuccess() throws Exception {
        ItemPedido itemPedido = ItemPedidoMock.getItemPedido();
        Pedido pedido = new Pedido();

        pedido.setItens(List.of(itemPedido));


        when(service.listarPedidos()).thenReturn(List.of(pedido));
        mockMvc.perform(get("/pedido/listar"))
                .andExpect(status().isOk());

    }

//    @Test
//    public void testListarPedidosNotFound() throws Exception {
//        when(service.listarPedidos()).thenReturn(new ArrayList<>());
//        mockMvc.perform(get("/pedido/listar"))
//                .andExpect(status().isNotFound());
//
//    }

}
