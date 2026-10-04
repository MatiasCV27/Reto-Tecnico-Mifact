package com.matias.Reto_Tecnico_Mifact.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ProductFilterDto {

    private String code;
    private String name;
    private String description;
    private String category;
    private Boolean enabled;

    private Double priceMin;
    private Double priceMax;

    private Integer stockMin;
    private Integer stockMax;
}
