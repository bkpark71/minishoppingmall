package org.example.minishoppingmall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateResponseDto {
    private int productId;
    private String productName;
    //private ProductStatus productStatus;
}
