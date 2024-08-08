package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.PedidoDTO;
import br.com.fiap.tech_challenge.core.domain.model.enums.SituacaoPedido;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PedidoMapper {

    @Mapping(source = "entity.itensPedido", target = "itens", qualifiedByName = "itensPedidoEntityToItensPedidoDto")
    @Mapping(source = "entity.situacao", target = "situacaoPedido")
    PedidoDTO toDTO(PedidoEntity entity);

    @Mapping(source = "pedidoDTO.itens", target = "itensPedido", qualifiedByName = "itensPedidoDtoToItensPedidoEntity")
    @Mapping(source = "pedidoDTO.situacaoPedido", target = "situacao")
    PedidoEntity toEntity(PedidoDTO pedidoDTO);

    @Named("itensPedidoEntityToItensPedidoDto")
    default List<ItemPedidoDTO> itensPedidoEntityToItensPedidoDto (List<ItemPedidoEntity> entities) {
        List<ItemPedidoDTO> itemPedidoDTOList = new ArrayList<>();
        entities.forEach(item -> {
            ItemPedidoDTO itemDTO = new ItemPedidoDTO();
            BeanUtils.copyProperties(item, itemDTO);
            itemDTO.getValorTotalItem();
            itemPedidoDTOList.add(itemDTO);
        });
        return itemPedidoDTOList;
    }

    @Named("itensPedidoDtoToItensPedidoEntity")
    default List<ItemPedidoEntity> itensPedidoDtoToItensPedidoEntity (List<ItemPedidoDTO> itensDTO) {
        List<ItemPedidoEntity> itemPedidoEntities = new ArrayList<>();
        itensDTO.forEach(item -> {
            ItemPedidoEntity entity = new ItemPedidoEntity();
            BeanUtils.copyProperties(item, entity);
            itemPedidoEntities.add(entity);
        });

        return itemPedidoEntities;
    }

}
