package com.matias.Reto_Tecnico_Mifact.service;

import com.matias.Reto_Tecnico_Mifact.exception.NotFoundException;
import com.matias.Reto_Tecnico_Mifact.mapper.ProductMapper;
import com.matias.Reto_Tecnico_Mifact.model.dto.PaginationQueryDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.PaginationResultDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.ProductDto;
import com.matias.Reto_Tecnico_Mifact.model.dto.ProductFilterDto;
import com.matias.Reto_Tecnico_Mifact.model.entity.ProductEntity;
import com.matias.Reto_Tecnico_Mifact.repository.ProductRepository;
import com.matias.Reto_Tecnico_Mifact.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ProductDto getProduct(String code) {

        log.info("Searching for product with code {}", code);

        return ProductMapper.toDto(repository.findByCode(code)
                .orElseThrow(() -> {
                    log.error("Product with code {} not found", code);
                    return new NotFoundException("Product with code " + code + " not found");
                }));
    }

    @Override
    public PaginationResultDto<ProductDto> searchProducts(PaginationQueryDto query, ProductFilterDto productFilter) {

        log.info("Searching for products with filter");

        PageRequest request = PageRequest.of(
                query.getPage(),
                query.getSize(),
                Sort.by(Sort.Direction.fromString(query.getDirection()), query.getSortBy())
        );

        Specification<ProductEntity> specification = Specification.where(
                ProductSpecification.byCode(productFilter.getCode())
                        .and(ProductSpecification.byName(productFilter.getName()))
                        .and(ProductSpecification.byDescription(productFilter.getDescription())
                                .and(ProductSpecification.byCategory(productFilter.getCategory()))
                                .and(ProductSpecification.byEnabled(productFilter.getEnabled()))
                                .and(ProductSpecification.byPrice(productFilter.getPriceMin(), productFilter.getPriceMax()))
                                .and(ProductSpecification.byStock(productFilter.getStockMin(), productFilter.getStockMax())))
        );

        Page<ProductEntity> page = repository.findAll(specification, request);

        log.info("Found {} products", page.getTotalElements());

        return new PaginationResultDto<>(
                page.getContent().stream().map(ProductMapper::toDto).collect(Collectors.toList()),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }

    @Override
    @Transactional
    public ProductDto saveProduct(ProductDto productDto) {

        log.info("Initiating creation product with code {}", productDto.getCode());

        if (repository.existsByCode(productDto.getCode())) {
            throw new IllegalArgumentException("Product with code " + productDto.getCode() + " already exists");
        }

        ProductEntity product = ProductMapper.toEntity(productDto);
        ProductEntity productSave = repository.save(product);

        log.info("Saving product with code {}", productSave.getCode());

        return ProductMapper.toDto(productSave);
    }

    @Override
    @Transactional
    public ProductDto updateProduct(ProductDto productDto, String code) {

        log.info("Initiating updating product with code {}", code);

        ProductEntity product = repository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Product with code " + code + " not found"));

        if (StringUtils.hasText(productDto.getName())) product.setName(productDto.getName());
        if (StringUtils.hasText(productDto.getDescription())) product.setDescription(productDto.getDescription());
        if (StringUtils.hasText(productDto.getCategory())) product.setCategory(productDto.getCategory());
        if (productDto.getPrice() != null) product.setPrice(productDto.getPrice());
        if (productDto.getStock() != null) product.setStock(productDto.getStock());
        if (productDto.getEnabled() != null) product.setEnabled(productDto.getEnabled());

        ProductEntity productUpdate = repository.save(product);

        log.info("Updating product with code {}", productUpdate.getCode());

        return ProductMapper.toDto(productUpdate);
    }

    @Override
    @Transactional
    public void deleteProduct(String code) {

        log.info("Initiating deleting product with code {}", code);

        if (repository.existsByCode(code)) {
            throw new IllegalArgumentException("Product with code " + code + " already exists");
        }

        ProductEntity product = repository.findByCode(code).orElse(null);

        assert product != null;
        repository.delete(product);
    }
}
