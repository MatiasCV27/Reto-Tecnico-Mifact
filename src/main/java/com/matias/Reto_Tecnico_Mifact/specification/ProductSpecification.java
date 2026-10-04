package com.matias.Reto_Tecnico_Mifact.specification;

import com.matias.Reto_Tecnico_Mifact.model.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<ProductEntity> byCode(String code) {
        return (root, query, cb) -> code == null ? null : cb.like(root.get("code"), "%" + code + "%");
    }

    public static Specification<ProductEntity> byName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ProductEntity> byDescription(String description) {
        return (root, query, cb) -> description == null ? null : cb.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<ProductEntity> byCategory(String category) {
        return (root, query, cb) -> category == null ? null : cb.like(root.get("category"), "%" + category + "%");
    }

    public static Specification<ProductEntity> byEnabled(Boolean enabled) {
        return (root, query, cb) -> enabled == null ? null : cb.equal(root.get("enabled"), enabled);
    }

    public static Specification<ProductEntity> byPrice(Double priceMin, Double priceMax) {
        return ((root, query, cb) -> priceMin == null || priceMax == null ? null : cb.between(root.get("price"), priceMin, priceMax));
    }

    public static Specification<ProductEntity> byStock(Integer stockMin, Integer stockMax) {
        return ((root, query, cb) -> stockMin == null || stockMax == null ? null : cb.between(root.get("stock"), stockMin, stockMax));
    }
}
