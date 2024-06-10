package org.example.minishoppingmall.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.example.minishoppingmall.dto.order.OrderInquiryDto;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepositoryQuery{
    @Autowired
    private EntityManager em;
    private String jpql = "select o from Order o ";

    public List<Order> findAllOrders() {
        return em.createQuery(jpql, Order.class).getResultList();
    }

    public List<OrderInquiryDto> findAllOrderDTOs() {  // fetch join 으로 쿼리 1번 발생
        jpql += "join fetch o.member m join fetch m.cart c";
        return em.createQuery(jpql, Order.class).getResultList().stream()
                .map(o -> new OrderInquiryDto(
                            o.getOrderId(),
                            o.getMember().getMemberId(),
                            o.getMember().getMemberName(), //// 추가해서 N+1 쿼리 여부 확인
                            o.getOrderDate(),
                            o.getTotalQuantity(),
                            o.getTotalPrice(),
                            o.getStatus(),
                            o.getStatusChangeDate()))
                .collect(Collectors.toList());
    }
}
