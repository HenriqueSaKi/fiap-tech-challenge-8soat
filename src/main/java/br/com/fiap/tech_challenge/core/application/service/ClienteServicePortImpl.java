package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.application.ports.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;
import br.com.fiap.tech_challenge.core.domain.ports.in.ClienteServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Optional;

@ComponentScan(basePackages = "br.com.fiap.tech_challenge.core.application.mapper")
@Service
public class ClienteServicePortImpl implements ClienteServicePort {

    private final ClienteRepositoryPort repository;
    private final ClienteMapper clienteMapper;

    @Autowired
    public ClienteServicePortImpl(ClienteRepositoryPort clienteRepositoryPort, ClienteMapper clienteMapper) {
        this.repository = clienteRepositoryPort;
        this.clienteMapper = clienteMapper;
    }

    @Override
    public void cadastrarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = clienteMapper.toEntity(clienteDTO);
        repository.save(clienteEntity);
    }

    @Override
    public ClienteDTO buscarClientePorCPF(String cpf) {
        Optional<ClienteEntity> clienteEntity = repository.buscarPorCpf(cpf);
        if(clienteEntity.isPresent()) {
            return clienteMapper.toDTO(clienteEntity.get());
        }
        return null;
    }

    @Override
    public void atualizarCliente(ClienteDTO clienteDTO) {
        Optional<ClienteEntity> clienteEntity = repository.findById(clienteDTO.getId());
        if(clienteEntity.isPresent()) {
            ClienteEntity entity = clienteMapper.toEntity(clienteDTO);
            repository.save(entity);
        }
    }

    @Override
    public boolean existeCliente(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void excluirCliente(Long id) {
        repository.excluirPorId(id);
    }


}
