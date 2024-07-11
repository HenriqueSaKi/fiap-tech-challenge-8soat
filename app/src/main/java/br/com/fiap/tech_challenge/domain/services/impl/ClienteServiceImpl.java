package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.Endereco;
import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;
import br.com.fiap.tech_challenge.infra.entity.EnderecoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);
    private final ClienteRepositoryPort repository;

    public ClienteServiceImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.repository = clienteRepositoryPort;
    }

    @Override
    public void cadastrarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(cliente, clienteEntity);

        List<EnderecoEntity> enderecoEntity = new ArrayList<>(cliente.getEnderecos().size());
        cliente.getEnderecos().forEach(endereco -> {
            EnderecoEntity entity = new EnderecoEntity();
            entity.setCep(endereco.getCep());
            entity.setLogradouro(endereco.getLogradouro());
            entity.setComplemento(endereco.getComplemento());
            entity.setBairro(endereco.getBairro());
            entity.setCidade(endereco.getCidade());
            entity.setEstado(endereco.getEstado());
            enderecoEntity.add(entity);
        });

        clienteEntity.setEnderecos(enderecoEntity);
        repository.save(clienteEntity);
    }

    @Override
    public Cliente buscarClientePorCPF(String cpf) {
        Optional<ClienteEntity> clienteEntity = repository.buscarPorCpf(cpf);
        if(clienteEntity.isPresent()) {
            Cliente cliente = new Cliente();
            BeanUtils.copyProperties(clienteEntity.get(), cliente);

            List<Endereco> enderecos = new ArrayList<>(clienteEntity.get().getEnderecos().size());
            clienteEntity.get().getEnderecos().forEach(e -> {
                Endereco endereco = new Endereco();
                endereco.setCep(e.getCep());
                endereco.setLogradouro(e.getLogradouro());
                endereco.setComplemento(e.getComplemento());
                endereco.setBairro(e.getBairro());
                endereco.setCidade(e.getCidade());
                endereco.setEstado(e.getEstado());
                enderecos.add(endereco);

            });

            cliente.setEnderecos(enderecos);
            return cliente;
        }
        return null;
    }

    @Override
    public void atualizarCliente(Cliente cliente) {
        Optional<ClienteEntity> clienteEntity = repository.findById(cliente.getId());
        if(clienteEntity.isPresent()) {
            ClienteEntity entity = clienteEntity.get();
            BeanUtils.copyProperties(cliente, entity);

            List<EnderecoEntity> enderecoEntities = new ArrayList<>(cliente.getEnderecos().size());
            cliente.getEnderecos().forEach(e -> {
                EnderecoEntity enderecoEntity = new EnderecoEntity();
                enderecoEntity.setCep(e.getCep());
                enderecoEntity.setLogradouro(e.getLogradouro());
                enderecoEntity.setComplemento(e.getComplemento());
                enderecoEntity.setBairro(e.getBairro());
                enderecoEntity.setCidade(e.getCidade());
                enderecoEntity.setEstado(e.getEstado());
                enderecoEntities.add(enderecoEntity);
            });

            entity.setEnderecos(enderecoEntities);
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
