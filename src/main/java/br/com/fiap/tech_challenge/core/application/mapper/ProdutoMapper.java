package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.CategoriaProdutoEntity;
import br.com.fiap.tech_challenge.core.domain.model.ProdutoDTO;
import br.com.fiap.tech_challenge.core.domain.model.CategoriaProduto;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProdutoMapper {

    @Mapping(source = "entity.categoria", target = "categoriaProduto", qualifiedByName = "categoriaEntityToCategoriaDto")
    ProdutoDTO toDTO(ProdutoEntity entity);

    @Mapping(source = "produtoDTO.categoriaProduto", target = "categoria", qualifiedByName = "categoriaDtoToCategoriaEntity")
    ProdutoEntity toEntity(ProdutoDTO produtoDTO);

    @Named("categoriaEntityToCategoriaDto")
    default CategoriaProduto categoriaEntityToCategoriaDto(CategoriaProdutoEntity categoriaEntity) {
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        BeanUtils.copyProperties(categoriaEntity, categoriaProduto);
        return categoriaProduto;
    }

    @Named("categoriaDtoToCategoriaEntity")
    default CategoriaProdutoEntity categoriaDtoToCategoriaEntity(CategoriaProduto categoriaProduto) {
        CategoriaProdutoEntity categoriaEntity = new CategoriaProdutoEntity();
        BeanUtils.copyProperties(categoriaProduto, categoriaEntity);
        return categoriaEntity;
    }
}
