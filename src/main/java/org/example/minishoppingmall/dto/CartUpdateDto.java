package org.example.minishoppingmall.dto;

import lombok.Data;

@Data
public class CartUpdateDto {
    private int cartProdId;
    private int quantity;
}
