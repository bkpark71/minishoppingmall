package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;
    @Column(length = 20)
    private String productName;
    private int cost;
    private int price;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    // 상품등록일, 상태변경일, ... 생략

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
