package org.example.minishoppingmall.service;

import org.assertj.core.api.Assertions;
import org.example.minishoppingmall.dto.MemberCreateDto;
import org.example.minishoppingmall.dto.OrderProductCreateDto;
import org.example.minishoppingmall.dto.StockCreateDto;
import org.example.minishoppingmall.entity.*;
import org.example.minishoppingmall.exception.ValidationCheckException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired OrderService orderService;
    @Autowired MemberService memberService;
    @Autowired ProductService productService;
    @Autowired StockService stockService;
    @Test
    void 주문생성테스트(){
        //given - 회원, 제품, 재고 생성
        //회원정보 생성 -
        MemberCreateDto memberDto = new MemberCreateDto(
                "test member","testId","1111","00000000000","test@test.com","test address"
        );
        int memberId = memberService.addMember(memberDto);
        //상품정보 생성
        Product product = new Product(0,"test product", 100,200, ProductStatus.A);
        int prodId = productService.addProduct(product);
        // KR warehouse에 대한 테스트 상품 초기 재고
        StockCreateDto stockDto = new StockCreateDto("KR", prodId, 10000);
        int stockId = stockService.addStock(stockDto);
        //when

        OrderProductCreateDto orderDto = new OrderProductCreateDto(memberId, prodId, 10, product.getPrice());
        Order order = orderService.createOrder(orderDto);
        //then
        assertThat(order.getOrderProducts().size()).isEqualTo(1);
        assertThat(order.getDelivery().getAddress()).isEqualTo(memberDto.getAddress());
    }

}