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
public class PedidoRepositoryImplTest {

    @Mock private PedidoRepository repository;
    @InjectMocks private PedidoRepositoryImpl pedidoRepository;

    @BeforeEach
    public void setUp() {
        pedidoRepository = new PedidoRepositoryImpl(repository);
    }

    @Test
    public void testCadastrarPedidos() {
        pedidoRepository.cadastrarPedidos(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testBuscarPorCpf() {
        pedidoRepository.listaPedidos();
        verify(repository, times(1)).findAll();
    }

}
