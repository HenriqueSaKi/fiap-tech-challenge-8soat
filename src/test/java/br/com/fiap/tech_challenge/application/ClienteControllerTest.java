package br.com.fiap.tech_challenge.application;

import br.com.fiap.tech_challenge.adapters.driver.controller.ClienteController;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.ports.in.ClienteServicePort;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteServicePort service;
    @InjectMocks
    private ClienteController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();

    }

    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/cliente/health"))
                .andExpect(status().isOk());

    }

    @Test
    public void testCadastrarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678910");
        cliente.setNomeCompleto("Teste Teste");
        cliente.setEmail("teste@exemplo.com.br");

        mockMvc.perform(
                        post("/cliente/")
                                .contentType("application/json")
                                .content(new Gson().toJson(cliente)))
                .andExpect(status().isCreated());

    }

    @Test
    public void testBuscarClientePorCpfSuccess() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678910");


        when(service.buscarClientePorCPF(any())).thenReturn(cliente);
        mockMvc.perform(get("/cliente/{cpf}", "12345678910"))
                .andExpect(status().isOk());

    }

    @Test
    public void testBuscarClientePorCpfCustomerNotFound() throws Exception {
        when(service.buscarClientePorCPF(any())).thenReturn(null);
        mockMvc.perform(get("/cliente/{cpf}", "12345678910"))
                .andExpect(status().isNotFound());

    }

    @Test
    public void testAtualizarCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678910");
        cliente.setNomeCompleto("Teste Atualizar");
        cliente.setEmail("teste@exemplo.com.br");

        mockMvc.perform(
                        put("/cliente/")
                                .contentType("application/json")
                                .content(new Gson().toJson(cliente)))
                .andExpect(status().isAccepted());

    }

    @Test
    public void testAtualizarClienteBadRequest() throws Exception {
        mockMvc.perform(put("/cliente/"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testExcluirCliente() throws Exception {
        when(service.existeCliente(any())).thenReturn(true);
        mockMvc.perform(delete("/cliente/id/{id}", 1L))
                .andExpect(status().isOk());

    }

    @Test
    public void testExcluirClienteNotFound() throws Exception {
        when(service.existeCliente(any())).thenReturn(false);
        mockMvc.perform(delete("/cliente/id/{id}", 1L))
                .andExpect(status().isNotFound());

    }


}
