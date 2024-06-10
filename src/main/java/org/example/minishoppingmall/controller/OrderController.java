package org.example.minishoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.order.OrderCreateResponseDto;
import org.example.minishoppingmall.dto.order.OrderProductCreateDto;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/products")
    public OrderCreateResponseDto addNewOrderProduct(@RequestBody OrderProductCreateDto orderProductDto){
        Order order = orderService.createOrder(orderProductDto);
        return new OrderCreateResponseDto(
                order.getOrderId(),
                order.getMember().getMemberId(),
                order.getOrderDate(),
                order.getTotalQuantity(),
                order.getTotalPrice(),
                order.getStatus()
                );
    }
}
