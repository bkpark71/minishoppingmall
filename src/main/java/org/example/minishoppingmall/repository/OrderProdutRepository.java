package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.dto.order.OrderProductInquiryDto;
import org.example.minishoppingmall.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProdutRepository extends JpaRepository<OrderProduct, Integer> {
    //List<OrderProduct> findAllByOrder_OrderId(int orderId);

//    @Query(value = "select oporg.example.minishoppingmall.entity.OrderProduct(" +
//            "op.orderProdId," +
//            "op.order.orderId, " +
//            "op.product.productId, " +
//            "op.product.productName, " +
//            "op.quantity, " +
//            "op.price, " +
//            "op.status) " +
//            "from OrderProduct op " +
//            "join fetch op.product p ")
    @Query(value = "select op from OrderProduct op " +
            "join fetch Product p on op.product.productId = p.productId " +
            "and op.order.orderId = :orderId ")
    List<OrderProduct> findAllByOrder_OrderId(@Param("orderId") int orderId);

}
