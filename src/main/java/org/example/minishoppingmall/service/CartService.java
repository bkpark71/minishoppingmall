package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.CartCreateDto;
import org.example.minishoppingmall.dto.CartUpdateDto;
import org.example.minishoppingmall.entity.Cart;
import org.example.minishoppingmall.entity.CartProduct;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.repository.CartProductRepository;
import org.example.minishoppingmall.repository.CartRepository;
import org.example.minishoppingmall.repository.MemberRepository;
import org.example.minishoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public List<CartProduct> getAllCartProducts(Cart cart) {
        return cartProductRepository.findAllByCart(cart);
    }

    public CartProduct getCartProductById(int cartProdID) {
        return cartProductRepository.findById(cartProdID).get();
    }

    @Transactional
    public void addCartProduct(CartCreateDto cartDto) { // 쇼핑화면에서 장바구니 추가를 눌렀을때
        CartProduct findCartProd = cartProductRepository.findByCart_CartIdAndProduct_ProductId(cartDto.getCartId(), cartDto.getProductId());
        if(findCartProd == null) {
            Cart cart = cartRepository.findById(cartDto.getCartId()).get();
            Product product = productRepository.findById(cartDto.getProductId()).get();
            CartProduct findCartProduct = CartProduct.addCartProduct(cart, product, cartDto.getQuantity(), cartDto.getPrice());
        } else {
            findCartProd.increaseQuantity(cartDto.getQuantity());
        }
        cartProductRepository.save(findCartProd);
    }

    @Transactional
    public void updateCartProduct(CartUpdateDto cartDto) { // 화면에서 수량을 변경한 내용으로 바로 update
        // 재고 체크를 해야 하는 상황이라면, 선택한 상품의 재고보다 큰 수량을
        // 장바구니에 담지 못하게 한다.
        CartProduct cartProduct = cartProductRepository.findById(cartDto.getCartProdId()).get();
        cartProduct.setQuantity(cartDto.getQuantity());
        cartProductRepository.save(cartProduct);
    }

}
