package org.example.minishoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.order.OrderCreateResponseDto;
import org.example.minishoppingmall.dto.order.OrderInquiryDto;
import org.example.minishoppingmall.dto.order.OrderProductCreateDto;
import org.example.minishoppingmall.dto.order.OrderProductInquiryDto;
import org.example.minishoppingmall.entity.Order;
import org.example.minishoppingmall.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

//    @GetMapping  // 모든 회원의 전체 주문 조회 : 주문(1) + 회원(2) + 장바구니(2)
//    public List<OrderInquiryDto> getAllMembersOrders(){
//        return orderService.getAllMembersOrders()
//                .stream()
//                .map(o -> new OrderInquiryDto(
//                        o.getOrderId(),
//                        o.getMember().getMemberId(),
//                        o.getMember().getMemberName(), //// 추가해서 N+1 쿼리 여부 확인
//                        o.getOrderDate(),
//                        o.getTotalQuantity(),
//                        o.getTotalPrice(),
//                        o.getStatus(),
//                        o.getStatusChangeDate()))
//                .collect(Collectors.toList());
//    }

    @GetMapping  // 모든 회원의 전체 주문 조회, fetch join 으로 한번만 쿼리 발생
    public List<OrderInquiryDto> getAllMembersOrdersDtos(){
        return orderService.getAllMembersOrderDtos();
    }

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

    @GetMapping("/{memberId}")
    public List<OrderInquiryDto> getOrdersByMember(
            @PathVariable("memberId") int memberId) {
        return orderService.getAllOrdersByMember(memberId)
                .stream()
                .map(o -> new OrderInquiryDto(
                        o.getOrderId(),
                        o.getMember().getMemberId(),
                        o.getMember().getMemberName(), //// 추가해서 N+1 쿼리 여부 확인
                        o.getOrderDate(),
                        o.getTotalQuantity(),
                        o.getTotalPrice(),
                        o.getStatus(),
                        o.getStatusChangeDate()))
                .collect(Collectors.toList());
    }



    @GetMapping("/products/{orderId}")
    public List<OrderProductInquiryDto> getOrderProductsByOrder(
            @PathVariable("orderId") int orderId) {
        return orderService.getAllOrderProductsByOrder(orderId)
                .stream()
                .map(o -> new OrderProductInquiryDto(
                        o.getOrderProdId(),
                        o.getOrder().getOrderId(),
                        o.getProduct().getProductId(),
                        o.getProduct().getProductName(),
                        o.getQuantity(),
                        o.getPrice(),
                        o.getStatus()))
                .collect(Collectors.toList());
    }

}
