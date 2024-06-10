package org.example.minishoppingmall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.ProductStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDto {
    private int productId;
    private ProductStatus status;
}
