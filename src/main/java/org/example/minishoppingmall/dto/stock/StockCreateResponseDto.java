package org.example.minishoppingmall.dto.stock;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockCreateResponseDto {
    private int stockId;
    private String warehouse;
    private int productId;
}
