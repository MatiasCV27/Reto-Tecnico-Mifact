package com.matias.Reto_Tecnico_Mifact.repository;

import com.matias.Reto_Tecnico_Mifact.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    boolean existsByCode(String code);

    Optional<ProductEntity> findByCode(String code);

    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);
}
