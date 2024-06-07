package org.example.minishoppingmall.dto;

import lombok.Data;

@Data
public class CartCreateDto {
    private int cartId;
    private int productId;
    private int quantity;
    public int price;
}
