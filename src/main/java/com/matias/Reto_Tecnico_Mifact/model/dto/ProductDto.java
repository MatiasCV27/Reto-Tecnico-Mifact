package com.matias.Reto_Tecnico_Mifact.model.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String code;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
    private LocalDate createDate;
    private Boolean enabled;
}
