package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.core.application.exception.cliente.ClienteNaoEncontradoException;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import br.com.fiap.tech_challenge.core.domain.model.Endereco;
import br.com.fiap.tech_challenge.core.domain.model.Telefone;
import br.com.fiap.tech_challenge.core.domain.mock.ClienteMock;
import br.com.fiap.tech_challenge.core.domain.mock.EnderecoMock;
import br.com.fiap.tech_challenge.core.domain.mock.TelefoneMock;
import br.com.fiap.tech_challenge.core.application.ports.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.EnderecoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.TelefoneEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock.ClienteEntityMock;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock.EnderecoEntityMock;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.mock.TelefoneEntityMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServicePortImplTest {

    @Mock private ClienteRepositoryPort repositoryPort;
    @Mock private ClienteMapper clienteMapper;
    @InjectMocks private ClienteServicePortImpl service;

    @BeforeEach
    public void setUp() {
        service = new ClienteServicePortImpl(repositoryPort, clienteMapper);
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = ClienteMock.getCliente();
        Telefone telefone = TelefoneMock.getTelefone();
        Endereco endereco = EnderecoMock.getEndereco();
        cliente.setTelefones(List.of(telefone));
        cliente.setEnderecos(List.of(endereco));

        service.cadastrarCliente(cliente);
        verify(repositoryPort, times(1))
                .save(any());

    }

    @Test
    public void whenBuscarClientePorCPFisPresent_thenReturnCliente() {
        ClienteEntity clienteEntity = ClienteEntityMock.getClienteEntity();

        when(repositoryPort.buscarPorCpf(any()))
                .thenReturn(Optional.of(clienteEntity));
        when(clienteMapper.toDTO(any())).thenReturn(new Cliente());
        var cliente = service.buscarClientePorCPF(clienteEntity.getCpf());

        assertNotNull(cliente);
        assertInstanceOf(Cliente.class, cliente);

    }

//    @Test
//    public void whenBuscarClientePorCPFisNotPresent_thenReturnNull() {
//        when(repositoryPort.buscarPorCpf(any()))
//                .thenReturn(Optional.empty());
//        var cliente = service.buscarClientePorCPF(any());
//
//        assertNull(cliente);
//
//    }

//    @Test
//    public void testAtualizarCliente_whenClienteEntityIsPresent() {
//        ClienteEntity clienteEntity = ClienteEntityMock.getClienteEntity();
//        TelefoneEntity telefoneEntity = TelefoneEntityMock.getTelefoneEntity();
//        EnderecoEntity enderecoEntity = EnderecoEntityMock.getEstadoEntity();
//        clienteEntity.setTelefones(List.of(telefoneEntity));
//        clienteEntity.setEnderecos(List.of(enderecoEntity));
//
//        Cliente cliente = ClienteMock.getCliente();
//        Telefone telefone = TelefoneMock.getTelefone();
//        Endereco endereco = EnderecoMock.getEndereco();
//        cliente.setTelefones(List.of(telefone));
//        cliente.setEnderecos(List.of(endereco));
//
//        when(repositoryPort.findById(any()))
//                .thenReturn(Optional.of(clienteEntity));
//        service.atualizarCliente(cliente);
//
//        verify(repositoryPort, times(1))
//                .findById(any());
//        verify(repositoryPort, times(1))
//                .save(any());
//
//    }

//    @Test
//    public void testAtualizarCliente_whenClienteEntityIsNotPresent() {
//        Cliente cliente = ClienteMock.getCliente();
//        Telefone telefone = TelefoneMock.getTelefone();
//        Endereco endereco = EnderecoMock.getEndereco();
//        cliente.setTelefones(List.of(telefone));
//        cliente.setEnderecos(List.of(endereco));
//
//        when(repositoryPort.findById(any())).thenReturn(Optional.empty());
//        assertThrows(ClienteNaoEncontradoException.class, () ->
//            service.atualizarCliente(cliente));
//
//        verify(repositoryPort, times(1))
//                .existsById(any());
//
//    }

    @Test
    public void testExisteCliente() {
        service.existeCliente(any());
        verify(repositoryPort, times(1))
                .existsById(any());
    }

//    @Test
//    public void testExcluirCliente() {
//        service.excluirCliente(any());
//        verify(repositoryPort, times(1))
//                .excluirPorId(any());
//    }

}
