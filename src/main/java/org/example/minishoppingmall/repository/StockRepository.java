package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findByWarehouseAndProduct_ProductId(String warehouse, int productId);
    List<Stock> findByWarehouse(String warehouse);
    List<Stock> findByProduct_ProductId(int productId);
}
