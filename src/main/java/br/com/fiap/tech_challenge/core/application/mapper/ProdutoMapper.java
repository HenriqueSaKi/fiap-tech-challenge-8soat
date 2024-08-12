package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.ProdutoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProdutoMapper {

    @Mapping(source = "entity.categoria", target = "categoriaProduto")
    ProdutoDTO toDTO(ProdutoEntity entity);

    @Mapping(source = "produtoDTO.categoriaProduto", target = "categoria")
    ProdutoEntity toEntity(ProdutoDTO produtoDTO);
}
