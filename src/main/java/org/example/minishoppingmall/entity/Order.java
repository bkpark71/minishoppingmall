package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.exception.OrderCancelException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
    private LocalDateTime orderDate; // create date time
    // 전체주문수량, 총주문금액
    private long totalQuantity;
    private long totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private LocalDateTime statusChangeDate;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;
    // 결제가 있다면 결제도 cascadeType.All
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts ;
    // setter

    // 편의 메서드 - 주문생성시 배송이 생성되어야 하며 양방향이므로 (연관관계의 주인)주문정보에 배송이, 배송정보에 주문이 들어가야 함
    // 편의 메서드 - 주문생성시 결제가 생성되어야 하며 양방향이므로 (연관관계의 주인)주문정보에 배송이, 결제정보에 주문이 들어가야 함
    // 주문생성시 주문상품이 생성되어야 하며 양방향이므로 주문헤더에 주문상품리스트추가, (연관관계의 주인) 주문상품정보에 주문이 들어가야 함.
    public static Order createOrder(Member member,  Delivery delivery, OrderProduct...orderProducts){
        Order order = new Order(0, member, LocalDateTime.now(), 0, 0,
                OrderStatus.ordered, LocalDateTime.now(), delivery, new ArrayList<>());

        for(OrderProduct orderProduct : orderProducts){
            order.addProduct(orderProduct);
        }
        //배송정보에 주문이 들어가야 함
        delivery.setOrder(order);
        //결제정보에 주문이 들어가야 함
        return order;
    }

    private void addProduct(OrderProduct orderProduct) {
        //OrderProduct orderProduct_new = orderProduct.createOrderProduct(orderProduct);
        this.totalQuantity += orderProduct.getQuantity();
        this.totalPrice += orderProduct.getPrice() * orderProduct.getQuantity();
        this.orderProducts.add(orderProduct);
    }

    public void cancelOrder(){
        // 전체 주문 취소
        // 주문상태를 cancel 로 변경
        if(this.status.equals(OrderStatus.ordered)) {
            this.status = OrderStatus.canceled;
            this.statusChangeDate = LocalDateTime.now();
            //this.totalPrice = 0;
            //this.totalQuantity = 0;
        } else {
            throw new OrderCancelException("주문완료 상태의 주문만 취소가능합니다. 고객센터로 문의바랍니다.");
        }

    }
}
