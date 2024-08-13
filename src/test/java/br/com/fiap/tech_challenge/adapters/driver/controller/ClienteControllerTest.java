package br.com.fiap.tech_challenge.adapters.driver.controller;

import br.com.fiap.tech_challenge.adapters.driver.controller.mapper.ClienteDTOMapper;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.AtualizarClienteDTO;
import br.com.fiap.tech_challenge.adapters.driver.controller.model.request.CadastrarClienteDTO;
import br.com.fiap.tech_challenge.core.application.exception.cliente.ClienteNaoEncontradoException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock private ClienteServicePort service;
    @Mock private ClienteDTOMapper mapper;
    @InjectMocks private ClienteController controller;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .alwaysDo(print())
                .build();

    }

    @Test
    public void testCadastrarCliente() throws Exception {
        CadastrarClienteDTO cliente = new CadastrarClienteDTO();
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

//    @Test
//    public void testBuscarClientePorCpfCustomerNotFound() throws Exception {
//        when(service.buscarClientePorCPF(any())).thenThrow(ClienteNaoEncontradoException.class);
//        mockMvc.perform(get("/cliente/{cpf}", "12345678910"));
//
//    }

    @Test
    public void testAtualizarCliente() throws Exception {
        AtualizarClienteDTO cliente = new AtualizarClienteDTO();
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
        mockMvc.perform(delete("/cliente/id/{id}", 1L))
                .andExpect(status().isOk());

    }

//    @Test
//    public void testExcluirClienteNotFound() throws Exception {
//        when(service.excluirCliente(1L)).thenThrow(ClienteNaoEncontradoException.class);
//        mockMvc.perform(delete("/cliente/id/{id}", 1L))
//                .andExpect(status().isNotFound());
//
//    }


}
