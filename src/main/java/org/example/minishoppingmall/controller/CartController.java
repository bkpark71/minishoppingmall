package org.example.minishoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.cart.CartCreateDto;
import org.example.minishoppingmall.dto.cart.CartCreateResponseDto;
import org.example.minishoppingmall.entity.CartProduct;
import org.example.minishoppingmall.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/products")
    public CartCreateResponseDto addNewCartProduct(@RequestBody CartCreateDto cartDto){
        // refactoring 대상 ==> 바로 객체를 던지도록
        int cartProdId = cartService.addCartProduct(cartDto);
        CartProduct cartProduct = cartService.getCartProductById(cartProdId);
        return new CartCreateResponseDto(cartProdId,
                cartProduct.getProduct().getProductId(),
                cartProduct.getQuantity(),
                cartProduct.getPrice());
    }
}
