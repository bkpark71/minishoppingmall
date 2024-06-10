package org.example.minishoppingmall.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartCreateDto {
    private int cartId;
    private int productId;
    private int quantity;
    public int price;
}
