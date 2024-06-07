package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryId;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    private String address;
    @Enumerated
    private DelStatus status;
    private LocalDateTime dateTime;

    // setter
    public void setOrder(Order order) {
        this.order = order;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(DelStatus status) {// 상태변화는 비즈니스 로직에 맞게 구현 해야함
        this.status = status;
    }
}
