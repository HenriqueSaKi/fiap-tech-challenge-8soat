package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepositoryPort repository;

    public ClienteServiceImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.repository = clienteRepositoryPort;
    }

    @Override
    public void save(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        BeanUtils.copyProperties(cliente, entity);
        repository.save(entity);
    }

    @Override
    public Cliente findById(String cpf) {
        Optional<ClienteEntity> clienteEntity = repository.findById(cpf);
        if(clienteEntity.isPresent()) {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteEntity.get(), cliente);
            return cliente;
        }
        return null;
    }

    @Override
    public void deleteById(String cpf) {
        repository.deleteById(cpf);
    }

}
