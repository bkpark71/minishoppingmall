package org.example.minishoppingmall.service;

import org.example.minishoppingmall.dto.stock.StockCreateDto;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.entity.Stock;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class StockServiceTest {
    private static final Logger log = LoggerFactory.getLogger(StockServiceTest.class);
    @Autowired
    StockService stockService;

    @Autowired
    ProductService productService;

    //given
    //when
    //then

    @Test
    public void addStock(){
        //given
        Product product = productService.getProductById(1);
        //Stock stock= new Stock(0, "KR", product, 10000);
        StockCreateDto stockDto = new StockCreateDto("EN", 1, 10000);
        //when
        int stockId = stockService.addStock(stockDto);
        List<Stock> stocks = stockService.getAllStockByProductId(1);
        //then
//        assertThatThrownBy(() -> stockService.addStock(stockDto))
//                .hasMessage("동일한 재고 자료가 존재합니다.");
        assertThat(stocks.size()).isEqualTo(2);
    }

    @Test
    public void increaseStock(){
        //given
        Stock stock = stockService.getStockById(2);
        //when
        stockService.increaseStock(stock.getWarehouse(),1, 100);
        //then
        assertThat(stock.getQuantity()).isEqualTo(10100);
    }

    @Test
    public void decreaseStock(){
        //given
        Stock stock = stockService.getStockById(2);
        //when
        //stockService.decreaseStock(stock.getWarehouse(),1, 100);
        //then
        //assertThat(stock.getQuantity()).isEqualTo(9900);
        assertThatThrownBy(() -> stockService.decreaseStock(stock.getWarehouse(),1, 20000))
                .hasMessage("재고 수량 부족");
    }
}