package com.matias.Reto_Tecnico_Mifact.controller;

import com.matias.Reto_Tecnico_Mifact.model.dto.PaginationQueryDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.PaginationResultDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.ProductDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.ProductFilterDto;
import com.matias.Reto_Tecnico_Mifact.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{code}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String code) {
        return ResponseEntity.ok(productService.getProduct(code));
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationResultDto<ProductDto>> searchProducts(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean enabled,
            @RequestParam(required = false) Double priceMin,
            @RequestParam(required = false) Double priceMax,
            @RequestParam(required = false) Integer stockMin,
            @RequestParam(required = false) Integer stockMax
    ) {
        PaginationQueryDto paginationQueryDto = new PaginationQueryDto(pageNumber, pageSize, sortBy, direction);
        ProductFilterDto productFilter = new ProductFilterDto(code, name, description, category, enabled,
                                                              priceMin, priceMax, stockMin, stockMax);

        return ResponseEntity.ok(productService.searchProducts(paginationQueryDto, productFilter));
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
        ProductDto saved = productService.saveProduct(productDto);
        URI location = URI.create("/api/orders/" + saved.getCode());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{code}")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto,
                                                  @PathVariable String code) {
        return ResponseEntity.ok(productService.updateProduct(productDto, code));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String code) {
        productService.deleteProduct(code);
        return ResponseEntity.noContent().build();
    }

}
