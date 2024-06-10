package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.dto.order.OrderInquiryDto;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepositoryQuery {
    public List<Order> findAllOrders() ;
    public List<OrderInquiryDto> findAllOrderDTOs(); // fetch join 으로 쿼리 1번 발생
}
