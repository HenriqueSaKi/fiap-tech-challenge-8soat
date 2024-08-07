package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.EnderecoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.TelefoneEntity;
import br.com.fiap.tech_challenge.core.application.ports.repository.ClienteRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;
import br.com.fiap.tech_challenge.core.domain.model.EnderecoDTO;
import br.com.fiap.tech_challenge.core.domain.model.TelefoneDTO;
import br.com.fiap.tech_challenge.core.domain.ports.in.ClienteServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicePortImpl implements ClienteServicePort {

    private final ClienteRepositoryPort repository;

    @Autowired
    public ClienteServicePortImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.repository = clienteRepositoryPort;
    }

    @Override
    public void cadastrarCliente(ClienteDTO clienteDTO) {
        ClienteEntity clienteEntity = new ClienteEntity();
        BeanUtils.copyProperties(clienteDTO, clienteEntity);
        clienteEntity.setCpf(clienteDTO.getCpf());

        List<TelefoneEntity> telefoneEntity = new ArrayList<>(clienteDTO.getTelefoneDTOS().size());
        clienteDTO.getTelefoneDTOS().forEach(telefoneDTO -> {
            TelefoneEntity entity = new TelefoneEntity();
            entity.setTipoTelefone(telefoneDTO.getTipoTelefone());
            entity.setDdd(telefoneDTO.getDdd());
            entity.setNumero(telefoneDTO.getNumero());
            telefoneEntity.add(entity);

        });

        List<EnderecoEntity> enderecoEntity = new ArrayList<>(clienteDTO.getEnderecoDTOS().size());
        clienteDTO.getEnderecoDTOS().forEach(enderecoDTO -> {
            EnderecoEntity entity = new EnderecoEntity();
            entity.setCep(enderecoDTO.getCep());
            entity.setLogradouro(enderecoDTO.getLogradouro());
            entity.setComplemento(enderecoDTO.getComplemento());
            entity.setBairro(enderecoDTO.getBairro());
            entity.setCidade(enderecoDTO.getCidade());
            entity.setEstado(enderecoDTO.getEstado());
            enderecoEntity.add(entity);
        });

        clienteEntity.setTelefones(telefoneEntity);
        clienteEntity.setEnderecos(enderecoEntity);
        repository.save(clienteEntity);
    }

    @Override
    public ClienteDTO buscarClientePorCPF(String cpf) {
        Optional<ClienteEntity> clienteEntity = repository.buscarPorCpf(cpf);
        if(clienteEntity.isPresent()) {
            ClienteDTO clienteDTO = new ClienteDTO();
            BeanUtils.copyProperties(clienteEntity.get(), clienteDTO);

            List<TelefoneDTO> telefoneDTOS = new ArrayList<>(clienteEntity.get().getTelefones().size());
            clienteEntity.get().getTelefones().forEach(t -> {
                TelefoneDTO telefoneDTO = new TelefoneDTO();
                telefoneDTO.setTipoTelefone(t.getTipoTelefone());
                telefoneDTO.setDdd(t.getDdd());
                telefoneDTO.setNumero(t.getNumero());
                telefoneDTOS.add(telefoneDTO);

            });


            List<EnderecoDTO> enderecoDTOS = new ArrayList<>(clienteEntity.get().getEnderecos().size());
            clienteEntity.get().getEnderecos().forEach(e -> {
                EnderecoDTO enderecoDTO = new EnderecoDTO();
                enderecoDTO.setCep(e.getCep());
                enderecoDTO.setLogradouro(e.getLogradouro());
                enderecoDTO.setComplemento(e.getComplemento());
                enderecoDTO.setBairro(e.getBairro());
                enderecoDTO.setCidade(e.getCidade());
                enderecoDTO.setEstado(e.getEstado());
                enderecoDTOS.add(enderecoDTO);

            });

            clienteDTO.setTelefoneDTOS(telefoneDTOS);
            clienteDTO.setEnderecoDTOS(enderecoDTOS);
            return clienteDTO;
        }
        return null;
    }

    @Override
    public void atualizarCliente(ClienteDTO clienteDTO) {
        Optional<ClienteEntity> clienteEntity = repository.findById(clienteDTO.getId());
        if(clienteEntity.isPresent()) {
            ClienteEntity entity = clienteEntity.get();
            BeanUtils.copyProperties(clienteDTO, entity);

            List<EnderecoEntity> enderecoEntities = new ArrayList<>(clienteDTO.getEnderecoDTOS().size());
            clienteDTO.getEnderecoDTOS().forEach(e -> {
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
