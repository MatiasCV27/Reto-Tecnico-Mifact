package com.matias.Reto_Tecnico_Mifact.mapper;

import com.matias.Reto_Tecnico_Mifact.model.dto.ProductDto;
import com.matias.Reto_Tecnico_Mifact.model.entity.ProductEntity;

public class ProductMapper {

    public static ProductDto toDto(ProductEntity entity) {
        if (entity == null) return null;

        return ProductDto.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .category(entity.getCategory())
                .createDate(entity.getCreateDate())
                .enabled(entity.getEnabled())
                .build();
    }

    public static ProductEntity toEntity(ProductDto dto) {
        if (dto == null) return null;

        return ProductEntity.builder()
                .code(dto.getCode())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(dto.getCategory())
                .enabled(true)
                .build();
    }
}
