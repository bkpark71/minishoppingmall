package org.example.minishoppingmall.service;

import org.assertj.core.api.Assertions;
import org.example.minishoppingmall.dto.CartCreateDto;
import org.example.minishoppingmall.dto.CartUpdateDto;
import org.example.minishoppingmall.dto.MemberCreateDto;
import org.example.minishoppingmall.entity.Cart;
import org.example.minishoppingmall.entity.CartProduct;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.entity.ProductStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {
    @Autowired
    CartService cartService;
    @Autowired
    MemberService memberService;
    @Autowired
    ProductService productService;
    //given
    //when
    //then
    @Test
    void 장바구니담기(){ // 상품을 처음 장바구니에 담기
        //given
        //회원정보 생성 - 장바구니 생성
        MemberCreateDto memberDto = new MemberCreateDto(
                "test member","testId","1111","00000000000","test@test.com","test address"
        );
        int memberId = memberService.addMember(memberDto);
        Cart cart = memberService.getMemberById(memberId).getCart();
        int cartId = cart.getCartId();
        //상품정보 생성
        Product product = new Product(0,"test product", 100,200, ProductStatus.A);
        int prodId = productService.addProduct(product);
        // 장바구니에 담을 상품정보
        CartCreateDto cartDto = new CartCreateDto(cartId,prodId,1,product.getPrice());
        //when
        cartService.addCartProduct(cartDto);
        List<CartProduct> cartProducts = cartService.getAllCartProducts(cart);
        //then
        assertThat(cartProducts.size()).isEqualTo(1);
    }

    @Test
    void 장바구니추가() { // 이미 장바구니에 담겨있는 상품에 대해 추가로 장바구니 담기 버튼 클릭 ==> 수량 증가
        //given
        //회원정보 생성 - 장바구니 생성
        MemberCreateDto memberDto = new MemberCreateDto(
                "test member","testId","1111","00000000000","test@test.com","test address"
        );
        int memberId = memberService.addMember(memberDto);
        Cart cart = memberService.getMemberById(memberId).getCart();
        int cartId = cart.getCartId();
        //상품정보 생성
        Product product = new Product(0,"test product", 100,200, ProductStatus.A);
        int prodId = productService.addProduct(product);
        // 장바구니에 이미 상품정보가 담겨짐
        CartCreateDto cartDto = new CartCreateDto(cartId,prodId,1,product.getPrice());
        cartService.addCartProduct(cartDto);
        //when
        // 다시 장바구니에 동일한 상품을 담음
        cartDto = new CartCreateDto(cartId,prodId,1,product.getPrice());
        cartService.addCartProduct(cartDto);
        List<CartProduct> cartProducts = cartService.getAllCartProducts(cart);
        //then
        assertThat(cartProducts.size()).isEqualTo(1);
        assertThat(cartProducts.get(0).getQuantity()).isEqualTo(2);
    }

    @Test
    void 장바구니에서_수량_변경(){
        //given
        //when
        //then
        CartUpdateDto cartDto = new CartUpdateDto();
    }
}