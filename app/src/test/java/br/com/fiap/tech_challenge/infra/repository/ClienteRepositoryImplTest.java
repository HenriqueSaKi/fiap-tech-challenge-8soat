package br.com.fiap.tech_challenge.infra.repository;

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
public class ClienteRepositoryImplTest {
    @Mock private ClienteRepository repository;
    @InjectMocks private ClienteRepositoryImpl clienteRepository;

    @BeforeEach
    public void setUp() {
        clienteRepository = new ClienteRepositoryImpl(repository);
    }

    @Test
    public void testFindById() {
        clienteRepository.findById(any());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void testBuscarPorCpf() {
        clienteRepository.buscarPorCpf(any());
        verify(repository, times(1)).findByCpf(any());
    }

    @Test
    public void testSave() {
        clienteRepository.save(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testExistsById() {
        clienteRepository.existsById(any());
        verify(repository, times(1)).existsById(any());
    }

    @Test
    public void testExcluirPorId() {
        clienteRepository.excluirPorId(any());
        verify(repository, times(1)).deleteById(any());
    }

}
