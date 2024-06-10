package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProdutRepository extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findAllByOrder_OrderId(int orderId);
}
