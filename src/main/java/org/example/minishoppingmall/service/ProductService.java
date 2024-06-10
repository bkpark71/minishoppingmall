package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.product.ProductCreateDto;
import org.example.minishoppingmall.dto.product.ProductUpdateDto;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.entity.ProductStatus;
import org.example.minishoppingmall.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int productId) {
        return productRepository.findById(productId).get();
    }

    @Transactional
    public int addProduct(ProductCreateDto productDto) {
        // 상품 최초 등록시에 판매가능(A)으로 등록한다고 가정
        Product product = new Product(0,productDto.getProductName(),
                productDto.getCost(),productDto.getPrice(), ProductStatus.A);
        Product save = productRepository.save(product);
        return save.getProductId();
    }

    @Transactional
    public void updateProduct(ProductUpdateDto productDto) {//status만 변경 가능
        Product product = productRepository.findById(productDto.getProductId()).get();
        product.setStatus(productDto.getStatus());
        productRepository.save(product);
    }
}
