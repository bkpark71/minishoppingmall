package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderProdId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
    private int quantity;
    private int price;
    private OrderStatus status;


    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public static OrderProduct createOrderProduct(Product product, int quantity, int price, Stock stock) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);
        orderProduct.setPrice(price);
        orderProduct.setStatus(OrderStatus.ordered);
        // setOrder(order);
        // 재고 감소
        stock.decreaseStock(quantity);
        return orderProduct;
    }

    public void cancelOrderProduct(Stock stock) {
        // 품목별 취소는 가능 단, ordered 상태일때만 가능, 결제했거나 배송중이면 취소할 수 없음
        // 재고 증가, 3-> 2 수량을 줄여서 (부분)취소 불가능
        this.setStatus(OrderStatus.canceled);
        stock.increaseStock(this.quantity);
        //this.order.getTotalQuantity() -= this.quantity;
        this.quantity = 0;
    }
}
