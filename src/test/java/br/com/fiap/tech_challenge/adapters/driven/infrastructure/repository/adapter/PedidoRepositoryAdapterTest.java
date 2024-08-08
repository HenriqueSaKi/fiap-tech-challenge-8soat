package br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.PedidoRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.adapter.PedidoRepositoryAdapter;
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
public class PedidoRepositoryAdapterTest {

    @Mock private PedidoRepository repository;
    @InjectMocks private PedidoRepositoryAdapter adapter;

    @BeforeEach
    public void setUp() {
        adapter = new PedidoRepositoryAdapter(repository);
    }

    @Test
    public void testCadastrarPedidos() {
        adapter.cadastrarPedidos(any());
        verify(repository, times(1)).save(any());
    }

    @Test
    public void testBuscarPorCpf() {
        adapter.listaPedidos();
        verify(repository, times(1)).findAll();
    }

}
