package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ClienteEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.EnderecoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.TelefoneEntity;
import br.com.fiap.tech_challenge.core.domain.model.ClienteDTO;
import br.com.fiap.tech_challenge.core.domain.model.EnderecoDTO;
import br.com.fiap.tech_challenge.core.domain.model.TelefoneDTO;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ClienteMapper {

    @Mapping(source = "entity.telefones", target = "telefoneDTOS", qualifiedByName = "telefoneEntityToTelefoneDto")
    @Mapping(source = "entity.enderecos", target = "enderecoDTOS", qualifiedByName = "enderecoEntityToEnderecoDto")
    ClienteDTO toDTO(ClienteEntity entity);

    @Mapping(source = "clienteDTO.telefoneDTOS", target = "telefones", qualifiedByName = "telefoneDtoToTelefoneEntity")
    @Mapping(source = "clienteDTO.enderecoDTOS", target = "enderecos", qualifiedByName = "enderecoDtoToEnderecoEntity")
    ClienteEntity toEntity(ClienteDTO clienteDTO);

    @Named("telefoneEntityToTelefoneDto")
    default List<TelefoneDTO> telefoneEntityToTelefoneDto(List<TelefoneEntity> telefoneEntities) {
        List<TelefoneDTO> telefoneDTOList = new ArrayList<>();
        telefoneEntities.forEach(telefone -> {
            TelefoneDTO telefoneDTO = new TelefoneDTO();
            BeanUtils.copyProperties(telefone, telefoneDTO);
            telefoneDTOList.add(telefoneDTO);
        });

        return telefoneDTOList;
    }

    @Named("enderecoEntityToEnderecoDto")
    default List<EnderecoDTO> enderecoEntityToEnderecoDto(List<EnderecoEntity> enderecoEntities) {
        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        enderecoEntities.forEach(endereco -> {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            BeanUtils.copyProperties(endereco, enderecoDTO);
            enderecoDTOList.add(enderecoDTO);
        });

        return enderecoDTOList;
    }

    @Named("telefoneDtoToTelefoneEntity")
    default List<TelefoneEntity> telefoneDtoToTelefoneEntity(List<TelefoneDTO> telefoneDTOList) {
        List<TelefoneEntity> telefoneEntities = new ArrayList<>();
        telefoneDTOList.forEach(telefone -> {
            TelefoneEntity entity = new TelefoneEntity();
            BeanUtils.copyProperties(telefone, entity);
            telefoneEntities.add(entity);
        });

        return telefoneEntities;
    }

    @Named("enderecoDtoToEnderecoEntity")
    default List<EnderecoEntity> enderecoDtoToEnderecoEntity(List<EnderecoDTO> enderecoDTOS) {
        List<EnderecoEntity> enderecoEntities = new ArrayList<>();
        enderecoDTOS.forEach(endereco -> {
            EnderecoEntity entity = new EnderecoEntity();
            BeanUtils.copyProperties(endereco, entity);
            enderecoEntities.add(entity);
        });

        return enderecoEntities;
    }

}
