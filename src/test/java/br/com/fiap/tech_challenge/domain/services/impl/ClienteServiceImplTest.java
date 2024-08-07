package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.Endereco;
import br.com.fiap.tech_challenge.domain.Telefone;
import br.com.fiap.tech_challenge.domain.mock.ClienteMock;
import br.com.fiap.tech_challenge.domain.mock.EnderecoMock;
import br.com.fiap.tech_challenge.domain.mock.TelefoneMock;
import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;
import br.com.fiap.tech_challenge.infra.entity.EnderecoEntity;
import br.com.fiap.tech_challenge.infra.entity.TelefoneEntity;
import br.com.fiap.tech_challenge.infra.entity.mock.ClienteEntityMock;
import br.com.fiap.tech_challenge.infra.entity.mock.EnderecoEntityMock;
import br.com.fiap.tech_challenge.infra.entity.mock.TelefoneEntityMock;
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
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepositoryPort repositoryPort;
    @InjectMocks
    private ClienteServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new ClienteServiceImpl(repositoryPort);
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
        TelefoneEntity telefoneEntity = TelefoneEntityMock.getTelefoneEntity();
        EnderecoEntity enderecoEntity = EnderecoEntityMock.getEstadoEntity();
        clienteEntity.setTelefones(List.of(telefoneEntity));
        clienteEntity.setEnderecos(List.of(enderecoEntity));

        when(repositoryPort.buscarPorCpf(any()))
                .thenReturn(Optional.of(clienteEntity));
        var cliente = service.buscarClientePorCPF(clienteEntity.getCpf());

        assertNotNull(cliente);
        assertInstanceOf(Cliente.class, cliente);

    }

    @Test
    public void whenBuscarClientePorCPFisNotPresent_thenReturnNull() {
        when(repositoryPort.buscarPorCpf(any()))
                .thenReturn(Optional.empty());
        var cliente = service.buscarClientePorCPF(any());

        assertNull(cliente);

    }

    @Test
    public void testAtualizarCliente_whenClienteEntityIsPresent() {
        ClienteEntity clienteEntity = ClienteEntityMock.getClienteEntity();
        TelefoneEntity telefoneEntity = TelefoneEntityMock.getTelefoneEntity();
        EnderecoEntity enderecoEntity = EnderecoEntityMock.getEstadoEntity();
        clienteEntity.setTelefones(List.of(telefoneEntity));
        clienteEntity.setEnderecos(List.of(enderecoEntity));

        Cliente cliente = ClienteMock.getCliente();
        Telefone telefone = TelefoneMock.getTelefone();
        Endereco endereco = EnderecoMock.getEndereco();
        cliente.setTelefones(List.of(telefone));
        cliente.setEnderecos(List.of(endereco));

        when(repositoryPort.findById(any()))
                .thenReturn(Optional.of(clienteEntity));
        service.atualizarCliente(cliente);

        verify(repositoryPort, times(1))
                .findById(any());
        verify(repositoryPort, times(1))
                .save(any());

    }

    @Test
    public void testAtualizarCliente_whenClienteEntityIsNotPresent() {
        Cliente cliente = ClienteMock.getCliente();
        Telefone telefone = TelefoneMock.getTelefone();
        Endereco endereco = EnderecoMock.getEndereco();
        cliente.setTelefones(List.of(telefone));
        cliente.setEnderecos(List.of(endereco));

        when(repositoryPort.findById(any())).thenReturn(Optional.empty());
        service.atualizarCliente(cliente);

        verify(repositoryPort, times(1))
                .findById(any());
        verify(repositoryPort, times(0))
                .save(any());

    }

    @Test
    public void testExisteCliente() {
        service.existeCliente(any());
        verify(repositoryPort, times(1))
                .existsById(any());
    }

    @Test
    public void testExcluirCliente() {
        service.excluirCliente(any());
        verify(repositoryPort, times(1))
                .excluirPorId(any());
    }

}
