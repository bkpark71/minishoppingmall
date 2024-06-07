package org.example.minishoppingmall.repository;

import org.example.minishoppingmall.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
}
