package org.example.minishoppingmall.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductCreateDto {
    private int memberId;
    private int productId;
    private int quantity;
    public int price;
}

