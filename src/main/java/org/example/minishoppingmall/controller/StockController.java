package org.example.minishoppingmall.controller;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.stock.StockCreateDto;
import org.example.minishoppingmall.dto.stock.StockCreateResponseDto;
import org.example.minishoppingmall.entity.Stock;
import org.example.minishoppingmall.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping
    public StockCreateResponseDto addNewProduct(@RequestBody StockCreateDto stockCreateDto){
        // refactoring 대상 ==> 바로 객체를 던지도록
        int stockId = stockService.addStock(stockCreateDto);
        Stock stock = stockService.getStockById(stockId);
        return new StockCreateResponseDto(stockId,
                stock.getWarehouse(),
                stock.getProduct().getProductId());
    }
}
