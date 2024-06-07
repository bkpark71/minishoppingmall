package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.OrderProductCreateDto;
import org.example.minishoppingmall.entity.*;
import org.example.minishoppingmall.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderProdutRepository orderProdutRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    
    public List<Order> getAllOrdersByMember(int memberId){
        return orderRepository.findAllByMember_MemberId(memberId);
    }
    
    public Order getOrderById(int orderId){
        return orderRepository.findById(orderId).get();
    }

    @Transactional
    public Order createOrder(OrderProductCreateDto orderProductDto){
        // 상품조회화면에서 바로 주문하기 
        Member member = memberRepository.findById(orderProductDto.getMemberId()).get();
        Product product = productRepository.findById(orderProductDto.getProductId()).get();
        Stock stock = stockRepository.findByWarehouseAndProduct_ProductId("KR", orderProductDto.getProductId());
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, orderProductDto.getQuantity(),
                orderProductDto.getPrice(), stock);

        // 처음 주문이라면 주문 생성
        Delivery delivery = new Delivery(0,null,
                member.getAddress(),DelStatus.A, LocalDateTime.now());
        Order order = Order.createOrder(member, delivery, orderProduct);
        orderProduct.setOrder(order);
        return orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(){
        //orderProduct 취소;
        //order취소;
    }
}
