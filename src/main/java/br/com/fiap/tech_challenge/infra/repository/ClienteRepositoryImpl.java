package br.com.fiap.tech_challenge.infra.repository;

import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteRepositoryImpl implements ClienteRepositoryPort {

    private final ClienteRepository repository;

    public ClienteRepositoryImpl(@Lazy ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ClienteEntity> findById(String cpf) {
        return repository.findById(cpf);
    }

    @Override
    public void save(ClienteEntity cliente) {
        repository.save(cliente);
    }

    @Override
    public void deleteById(String cpf) {
        repository.deleteById(cpf);
    }

}
