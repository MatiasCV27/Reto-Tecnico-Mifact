package com.matias.Reto_Tecnico_Mifact.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matias.Reto_Tecnico_Mifact.model.entity.ProductEntity;
import com.matias.Reto_Tecnico_Mifact.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository repository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        long count = repository.count();

        if (count == 0) {

            Resource resource = resourceLoader.getResource("classpath:products.json");
            List<ProductEntity> products = objectMapper.readValue(resource.getInputStream(), new TypeReference<List<ProductEntity>>() {
            });

            repository.saveAll(products);
        }
    }
}
