package org.example.minishoppingmall.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartUpdateDto {
    private int cartProdId;
    private int quantity;
}
