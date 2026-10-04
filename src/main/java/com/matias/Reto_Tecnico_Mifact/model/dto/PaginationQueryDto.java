package com.matias.Reto_Tecnico_Mifact.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class PaginationQueryDto {

    private int page;
    private int size;
    private String sortBy;
    private String direction;
}
