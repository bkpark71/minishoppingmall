package org.example.minishoppingmall.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.minishoppingmall.entity.OrderStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInquiryDto {
    private int orderId;
    private int memberId;
    private String memberName; // N+1 쿼리 테스트 위한 필드
    private LocalDateTime orderDate;
    private long totalQuantity;
    private long totalPrice;
    private OrderStatus status;
    private LocalDateTime statusChangeDate;
}
