package org.example.minishoppingmall.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartProdId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;
    private int quantity;
    private int price;

    public static CartProduct addCartProduct(Cart cart, Product product, int quantity, int price) {
        // cart 총 수량과 총 금액
        return new CartProduct(0, cart, product, quantity, price);
    }

    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    public void setQuantity(int quantity) {
        // 장바구니에 0 이어도 담겨져 있는다면 수량만 변경
        // 장바구니에서 담을 최소수량이 정해져있다면 그 내용도 반영해서 처리
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }
//    public void decreaseQuantity(int quantity) {
//        if (this.quantity >= quantity) {
//            this.quantity -= quantity;
//        }
//    }
}
