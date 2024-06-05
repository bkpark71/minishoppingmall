package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProdutRepository extends JpaRepository<OrderProduct, Integer> {
}
