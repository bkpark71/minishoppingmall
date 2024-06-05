package org.example.minishoppingmall.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockCreateDto {
    private String Warehouse;
    private int ProductId;
    private int quantity;
}
