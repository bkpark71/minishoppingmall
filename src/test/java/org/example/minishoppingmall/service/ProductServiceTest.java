package org.example.minishoppingmall.service;

import org.assertj.core.api.Assertions;
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
class ProductServiceTest {
    @Autowired
    ProductService productService;
    //given
    //when
    //then
    @Test
    public void addProduct(){
        //given
        Product product = new Product(0,"test", 100, 200, ProductStatus.A);
        //when
        productService.addProduct(product);
        List<Product> allProducts = productService.getAllProducts();
        //then
        assertThat(allProducts).hasSize(1);
    }
}