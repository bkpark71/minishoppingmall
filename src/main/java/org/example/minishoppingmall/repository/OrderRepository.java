package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.entity.OrderProduct;
import org.example.minishoppingmall.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByMember_MemberId(int memberId);
    List<Order> findAllByMember_MemberIdAndStatus(int memberId, OrderStatus status);
    List<Order> findAllByMember_MemberIdAndOrderDate(int memberId, LocalDateTime orderDate);
    //List<OrderProduct> findAllByOrder_OrderId(int orderId);
}
