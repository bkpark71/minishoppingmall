package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Cart;
import org.example.minishoppingmall.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByMember(Member member);
}
