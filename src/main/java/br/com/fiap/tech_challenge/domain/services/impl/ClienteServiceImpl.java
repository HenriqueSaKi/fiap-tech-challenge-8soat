package br.com.fiap.tech_challenge.domain.services.impl;

import br.com.fiap.tech_challenge.domain.Cliente;
import br.com.fiap.tech_challenge.domain.Endereco;
import br.com.fiap.tech_challenge.domain.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.domain.services.ClienteService;
import br.com.fiap.tech_challenge.infra.entity.ClienteEntity;
import br.com.fiap.tech_challenge.infra.entity.EnderecoEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepositoryPort repository;

    public ClienteServiceImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.repository = clienteRepositoryPort;
    }

    @Override
    public void save(Cliente cliente) {
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
    public Cliente findById(String cpf) {
        Optional<ClienteEntity> clienteEntity = repository.findByCpf(cpf);
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
    public void deleteById(String cpf) {
        repository.deleteByCpf(cpf);
    }

}
