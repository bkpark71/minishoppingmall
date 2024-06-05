package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.exception.NotEnoughStockException;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stockId;
    @Column(length = 10)
    private String warehouse;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
    private int quantity;

    public void manipulateQuantity(StockReason reason, int stockQuantity) {
        // reason 이 A 이면, 기존 재고에 더해줌 ==> 증가
        // reason 이 B 이면, 기존 재고에서 빼줌 ==> 감소, 마이너스 재고는 불가능함
        if(reason.equals(StockReason.A)) {
            quantity += stockQuantity;
        } else if (reason.equals(StockReason.B)) {
            if(quantity >= stockQuantity) {
                quantity -= stockQuantity;
            } else {
                throw new NotEnoughStockException("재고 수량 부족");
            }
        }

    }
}
