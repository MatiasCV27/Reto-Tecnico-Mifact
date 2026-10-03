package com.matias.Reto_Tecnico_Mifact.repository;

import com.matias.Reto_Tecnico_Mifact.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
