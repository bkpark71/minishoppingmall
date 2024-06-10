package org.example.minishoppingmall.repository;

import jakarta.persistence.EntityManager;
import org.example.minishoppingmall.entity.Member;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.entity.OrderProduct;
import org.example.minishoppingmall.entity.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>,
                                         OrderRepositoryQuery{

//    @Query(value = "select o from Order o " +
//            "join Member m on o.member.memberId = m.memberId " +
//            "join Cart c on m.cart.cartId = c.cartId")
//    List<Order> findAll();

//    @Query(value = "select o from Order o " +
//            "join Member m " +
//            "on m.memberId = o.member.memberId " +
//            "where o.member.memberId = :memberId ")
//    List<Order> findAllByMember_MemberId(@Param("memberId") int memberId);

    List<Order> findAllByMember_MemberId(int memberId);
    List<Order> findAllByMember_MemberIdAndStatus(int memberId, OrderStatus status);
    List<Order> findAllByMember_MemberIdAndOrderDate(int memberId, LocalDateTime orderDate);
    //List<OrderProduct> findAllByOrder_OrderId(int orderId);
}
