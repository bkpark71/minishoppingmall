package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStatus(ProductStatus status);
    List<Product> findByProductName(String productName);
}
