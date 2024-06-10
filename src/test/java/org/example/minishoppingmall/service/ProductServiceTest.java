package org.example.minishoppingmall.service;

import org.example.minishoppingmall.dto.product.ProductCreateDto;
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
class ProductServiceTest {
    @Autowired
    ProductService productService;
    //given
    //when
    //then
    @Test
    public void addProduct(){
        //given
        ProductCreateDto productDto = new ProductCreateDto("test", 100, 200, ProductStatus.A);
        //when
        productService.addProduct(productDto);
        List<Product> allProducts = productService.getAllProducts();
        //then
        assertThat(allProducts).hasSize(1);
    }
}