package org.example.minishoppingmall.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.OrderStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductInquiryDto {
    private int orderProdId;
    private int orderId;
    private int prodId;
    private String prodName;
    private int quantity;
    private int price;
    private OrderStatus status;
}
