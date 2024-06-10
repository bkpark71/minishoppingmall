package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.order.OrderProductCreateDto;
import org.example.minishoppingmall.entity.*;
import org.example.minishoppingmall.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderProdutRepository orderProdutRepository;
    private final MemberRepository memberRepository;
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    
    public List<Order> getAllOrdersByMember(int memberId){
        return orderRepository.findAllByMember_MemberId(memberId);
    }
    public List<OrderProduct> getAllOrderProductsByOrder(int orderId){
        return orderProdutRepository.findAllByOrder_OrderId(orderId);
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
    public void cancelOrder(int orderId){
        //전체 order취소;
        //orderProduct 전체 취소이므로 모든 주문상품에 대한 재고 증가
        Order order = orderRepository.findById(orderId).get();

        List<OrderProduct> orderProducts = order.getOrderProducts();
        for (OrderProduct orderProduct : orderProducts) {
            Stock stock = stockRepository.findByWarehouseAndProduct_ProductId("KR", orderProduct.getProduct().getProductId());
            orderProduct.cancelOrderProduct(stock);
        }

        order.cancelOrder();
        orderRepository.save(order);
    }

    @Transactional
    public void cancelOrderProduct(int orderProdId){
        // 주문상태가 ordered 일 경우에만 취소 가능, 배송중, 결제완료이면 반품처리해야 함
        //취소한 orderProduct에 대해서만 재고 증가
        //주문상품이 하나만 있는 경우는 전체취소와 동일
        OrderProduct orderProduct = orderProdutRepository.findById(orderProdId).get();
        Order order = orderProduct.getOrder();
        if(order.getStatus().equals(OrderStatus.ordered)){
            Stock stock = stockRepository.findByWarehouseAndProduct_ProductId("KR", orderProdId);
            orderProduct.cancelOrderProduct(stock);
        }

    }

}
