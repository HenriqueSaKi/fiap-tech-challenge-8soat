package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ClienteRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.ClienteRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ClienteRepositoryAdapterTest {
    @Mock private ClienteRepository repository;
    @InjectMocks private ClienteRepositoryAdapter clienteRepositoryAdapter;

    @BeforeEach
    public void setUp() {
        clienteRepositoryAdapter = new ClienteRepositoryAdapter(repository);
    }

    @Test
    public void testFindById() {
        clienteRepositoryAdapter.findById(any());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void testBuscarPorCpf() {
        clienteRepositoryAdapter.buscarPorCpf(any());
        verify(repository, times(1)).findByCpf(any());
    }

    @Test
    public void testSave() {
        clienteRepositoryAdapter.save(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testExistsById() {
        clienteRepositoryAdapter.existsById(any());
        verify(repository, times(1)).existsById(any());
    }

    @Test
    public void testExcluirPorId() {
        clienteRepositoryAdapter.excluirPorId(any());
        verify(repository, times(1)).deleteById(any());
    }

}
