package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.cart.CartCreateDto;
import org.example.minishoppingmall.dto.cart.CartUpdateDto;
import org.example.minishoppingmall.entity.Cart;
import org.example.minishoppingmall.entity.CartProduct;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.repository.CartProductRepository;
import org.example.minishoppingmall.repository.CartRepository;
import org.example.minishoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {
    private final CartProductRepository cartProductRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    public List<CartProduct> getAllCartProducts(Cart cart) {
        return cartProductRepository.findAllByCart(cart);
    }

    public CartProduct getCartProductById(int cartProdID) {
        return cartProductRepository.findById(cartProdID).get();
    }

    @Transactional
    public int addCartProduct(CartCreateDto cartDto) { // 쇼핑화면에서 장바구니 추가를 눌렀을때
        CartProduct findCartProd = cartProductRepository.findByCart_CartIdAndProduct_ProductId(cartDto.getCartId(), cartDto.getProductId());
        if(findCartProd == null) {
            Cart cart = cartRepository.findById(cartDto.getCartId()).get();
            Product product = productRepository.findById(cartDto.getProductId()).get();
            findCartProd = CartProduct.addCartProduct(cart, product, cartDto.getQuantity(), cartDto.getPrice());
        } else {
            findCartProd.increaseQuantity(cartDto.getQuantity());
        }
        return cartProductRepository.save(findCartProd).getCartProdId();
    }

    @Transactional
    public void updateCartProduct(CartUpdateDto cartDto) { // 화면에서 수량을 변경한 내용으로 바로 update
        // 장바구니에 담을 때도 재고 체크를 해야 하는 상황이라면, 선택한 상품의 재고보다 큰 수량을
        // 장바구니에 담지 못하게 한다.
        // 우리의 경우는 지역이 하나밖에 없지만 여러 warehouse가 있고, 재고 체크를 하려면
        // 회원의 살고 있는 지역을 담당하는 Warehouse를 찾아서 해당 warehouse의 상품 재고를 확인하는
        // 로직을 추가해야 한다.
        CartProduct cartProduct = cartProductRepository.findById(cartDto.getCartProdId()).get();
        cartProduct.setQuantity(cartDto.getQuantity());
        cartProductRepository.save(cartProduct);
    }

}
