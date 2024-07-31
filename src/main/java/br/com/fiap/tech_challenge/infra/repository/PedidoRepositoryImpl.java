package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.domain.repository.PedidoRepositoryPort;
import br.com.fiap.tech_challenge.infra.entity.PedidoEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoRepositoryImpl implements PedidoRepositoryPort {

    private final PedidoRepository repository;

    public PedidoRepositoryImpl(@Lazy PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void cadastrarPedidos(PedidoEntity pedido) {
        repository.save(pedido);
    }

    @Override
    public List<PedidoEntity> listaPedidos() {
        return repository.findAll();
    }

}
