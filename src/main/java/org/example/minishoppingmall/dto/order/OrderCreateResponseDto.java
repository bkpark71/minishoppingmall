package org.example.minishoppingmall.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.OrderStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponseDto {
    private int orderId;
    private int memberId;
    private LocalDateTime orderDate;
    private long totalQuantity;
    private long totalPrice;
    private OrderStatus status;
}
