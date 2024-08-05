package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.domain.repository.entity.ClienteEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryImpl implements ClienteRepositoryPort {
    // precisa retirar a redundância

    private final ClienteRepository repository;

    public ClienteRepositoryImpl(@Lazy ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ClienteEntity> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<ClienteEntity> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public void save(ClienteEntity cliente) {
        repository.save(cliente);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void excluirPorId(Long id) {
        repository.deleteById(id);
    }

}
