package com.matias.Reto_Tecnico_Mifact.service;

import com.matias.Reto_Tecnico_Mifact.model.dto.PaginationQueryDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.PaginationResultDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.ProductDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.ProductFilterDto;

public interface IProductService {

    ProductDto getProduct(String code);
    PaginationResultDto<ProductDto> searchProducts(PaginationQueryDto query, ProductFilterDto productFilter);
    ProductDto saveProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto, String code);
    void deleteProduct(String code);
}
