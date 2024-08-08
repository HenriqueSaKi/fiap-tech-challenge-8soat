package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;
import br.com.fiap.tech_challenge.core.domain.model.EnderecoDTO;
import br.com.fiap.tech_challenge.core.domain.model.TelefoneDTO;
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
        ClienteDTO clienteDTO = ClienteMock.getCliente();
        TelefoneDTO telefoneDTO = TelefoneMock.getTelefone();
        EnderecoDTO enderecoDTO = EnderecoMock.getEndereco();
        clienteDTO.setTelefoneDTOS(List.of(telefoneDTO));
        clienteDTO.setEnderecoDTOS(List.of(enderecoDTO));

        service.cadastrarCliente(clienteDTO);
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
        assertInstanceOf(ClienteDTO.class, cliente);

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

        ClienteDTO clienteDTO = ClienteMock.getCliente();
        TelefoneDTO telefoneDTO = TelefoneMock.getTelefone();
        EnderecoDTO enderecoDTO = EnderecoMock.getEndereco();
        clienteDTO.setTelefoneDTOS(List.of(telefoneDTO));
        clienteDTO.setEnderecoDTOS(List.of(enderecoDTO));

        when(repositoryPort.findById(any()))
                .thenReturn(Optional.of(clienteEntity));
        service.atualizarCliente(clienteDTO);

        verify(repositoryPort, times(1))
                .findById(any());
        verify(repositoryPort, times(1))
                .save(any());

    }

    @Test
    public void testAtualizarCliente_whenClienteEntityIsNotPresent() {
        ClienteDTO clienteDTO = ClienteMock.getCliente();
        TelefoneDTO telefoneDTO = TelefoneMock.getTelefone();
        EnderecoDTO enderecoDTO = EnderecoMock.getEndereco();
        clienteDTO.setTelefoneDTOS(List.of(telefoneDTO));
        clienteDTO.setEnderecoDTOS(List.of(enderecoDTO));

        when(repositoryPort.findById(any())).thenReturn(Optional.empty());
        service.atualizarCliente(clienteDTO);

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
