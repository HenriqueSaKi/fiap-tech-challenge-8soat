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

    @Mapping(source = "entity.produtoId", target = "produtoId")
    @Mapping(source = "entity.categoriaProduto", target = "categoria")
    ProdutoDTO toDTO(ProdutoEntity entity);

    @Mapping(source = "produtoDTO.produtoId", target = "produtoId")
    @Mapping(source = "produtoDTO.categoria", target = "categoriaProduto")
    ProdutoEntity toEntity(ProdutoDTO produtoDTO);

}
