package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.example.minishoppingmall.dto.ProductUpdateDto;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(ProductUpdateDto productDto) {//status만 변경 가능
        Product product = productRepository.findById(productDto.getProductId()).get();
        product.setStatus(productDto.getStatus());
        productRepository.save(product);
    }
}
