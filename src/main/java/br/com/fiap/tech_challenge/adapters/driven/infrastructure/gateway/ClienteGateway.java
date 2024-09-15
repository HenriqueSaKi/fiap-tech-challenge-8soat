package br.com.fiap.tech_challenge.adapters.driven.infrastructure.gateway;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.ClienteRepository;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.repository.entity.ClienteEntity;
import br.com.fiap.tech_challenge.core.application.mapper.ClienteMapper;
import br.com.fiap.tech_challenge.core.application.ports.gateway.ClienteGatewayPort;
import br.com.fiap.tech_challenge.core.domain.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClienteGateway implements ClienteGatewayPort {

  private ClienteRepository repository;
  private ClienteMapper clienteMapper;

  @Autowired
  public ClienteGateway(ClienteRepository repository, ClienteMapper clienteMapper) {
    this.repository = repository;
    this.clienteMapper = clienteMapper;
  }

  @Override
  public Cliente findById(Long id) {
    Optional<ClienteEntity> clienteEntity = repository.findById(id);
    return clienteEntity.map(entity -> clienteMapper.toCliente(entity)).orElse(null);
  }

  @Override
  public Cliente buscarPorCpf(String cpf) {
    Optional<ClienteEntity> clienteEntity = repository.findByCpf(cpf);
    return clienteEntity.map(entity -> clienteMapper.toCliente(entity)).orElse(null);
  }

  @Override
  public void save(Cliente cliente) throws Exception {
    try {
      ClienteEntity clienteEntity = clienteMapper.toClienteEntity(cliente);
      repository.save(clienteEntity);
    } catch (Exception e) {
      throw new Exception(e);
    }
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
