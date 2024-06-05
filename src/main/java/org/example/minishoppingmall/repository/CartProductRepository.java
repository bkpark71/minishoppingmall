package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
}
