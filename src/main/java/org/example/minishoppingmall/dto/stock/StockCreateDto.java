package org.example.minishoppingmall.dto.stock;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockCreateDto {
    private String warehouse;
    private int productId;
    private int quantity;
}
