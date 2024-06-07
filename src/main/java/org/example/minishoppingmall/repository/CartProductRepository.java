package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Cart;
import org.example.minishoppingmall.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;
import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
    List<CartProduct> findAllByCart(Cart cart);
    CartProduct findByCart_CartIdAndProduct_ProductId(int cartId, int productId);
}
