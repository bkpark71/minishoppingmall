package org.example.minishoppingmall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.ProductStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInquiryDto {
    private int productId;
    private String productName;
    private int cost;
    private int price;
    private ProductStatus productStatus;
}
