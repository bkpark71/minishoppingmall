package org.example.minishoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.product.ProductCreateDto;
import org.example.minishoppingmall.dto.product.ProductCreateResponseDto;
import org.example.minishoppingmall.dto.product.ProductInquiryDto;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ProductCreateResponseDto addNewProduct(@RequestBody ProductCreateDto productCreateDto){
        // refactoring 대상 ==> 바로 객체를 던지도록
        int productId = productService.addProduct(productCreateDto);
        Product product = productService.getProductById(productId);
        return new ProductCreateResponseDto(productId,product.getProductName());
    }

    @GetMapping
    public List<ProductInquiryDto> getProductInquiry(){
//        List<ProductInquiryDto> inquiryDtos = new ArrayList<>();
//        List<Product> allProducts = productService.getAllProducts();
//        for (Product product : allProducts) {
//            inquiryDtos.add(new ProductInquiryDto(product);
//        }
        return productService.getAllProducts().stream()
                .map(p -> new ProductInquiryDto(
                        p.getProductId(),
                        p.getProductName(),
                        p.getCost(),
                        p.getPrice(),
                        p.getStatus()))
                .collect(Collectors.toList());
    }
}
