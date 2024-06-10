package org.example.minishoppingmall.service;

import org.example.minishoppingmall.dto.cart.CartCreateDto;
import org.example.minishoppingmall.dto.cart.CartUpdateDto;
import org.example.minishoppingmall.dto.member.MemberCreateDto;
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
        //given - 이미 회원이 상품을 장바구니에 담았다는 가정하에
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
        int cartProdId = cartService.addCartProduct(cartDto);
        //when - 장바구니에 담긴 상품의 수량을 변경하면
        CartUpdateDto cartUpdateDto = new CartUpdateDto(cartProdId, 2);
        cartService.updateCartProduct(cartUpdateDto);
        CartProduct cartProduct = cartService.getCartProductById(cartProdId);
        //then - 반영이 되는지 체크
        assertThat(cartProduct.getQuantity()).isEqualTo(cartUpdateDto.getQuantity());
    }

    @Test
    void 장바구니_최소수량_체크(){
        //given - 이미 회원이 상품을 장바구니에 담았다는 가정하에
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
        int cartProdId = cartService.addCartProduct(cartDto);
        //when - 장바구니에 담긴 상품의 수량을 최소수량(1)보다 작게 변경하면
        CartUpdateDto cartUpdateDto = new CartUpdateDto(cartProdId, 0);
        cartService.updateCartProduct(cartUpdateDto);
        CartProduct cartProduct = cartService.getCartProductById(cartProdId);
        //then - 반영이 안되는지 체크, 즉 생성시에 입력한 수량이 변동이 없어야 함
        assertThat(cartProduct.getQuantity()).isEqualTo(cartDto.getQuantity());
    }
}