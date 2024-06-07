package org.example.minishoppingmall.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductCreateDto {
    private int memberId;
    private int productId;
    private int quantity;
    public int price;
}

