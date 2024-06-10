package org.example.minishoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.example.minishoppingmall.dto.stock.StockCreateDto;
import org.example.minishoppingmall.entity.Product;
import org.example.minishoppingmall.entity.Stock;
import org.example.minishoppingmall.entity.StockReason;
import org.example.minishoppingmall.exception.ValidationCheckException;
import org.example.minishoppingmall.repository.ProductRepository;
import org.example.minishoppingmall.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StockService {
    private final StockRepository stockRepository;
    private final ProductRepository productRepository;

    public List<Stock> getAllStockByWarehouse(String warehouse) {
        return stockRepository.findByWarehouse(warehouse);
    }

    public List<Stock> getAllStockByProductId(int productId) {
        return stockRepository.findByProduct_ProductId(productId);
    }

    public Stock getStockById(int stockId) {
        return stockRepository.findById(stockId).get();
    }

    @Transactional
    public int addStock(StockCreateDto stockDto) throws ValidationCheckException {
        Product product = productRepository.findById(stockDto.getProductId()).get();
        validationCheck(stockDto.getWarehouse(), stockDto.getProductId());
        Stock stock = new Stock(0,stockDto.getWarehouse(), product, stockDto.getQuantity());
        return stockRepository.save(stock).getStockId();
    }

    private void validationCheck(String warehouse, int productId) {
        Stock stock = stockRepository.findByWarehouseAndProduct_ProductId(warehouse, productId);
        if(stock != null) {
            throw new ValidationCheckException("동일한 재고 자료가 존재합니다.");
        }
    }

    @Transactional
    public void increaseStock(String warehouse, int productId, int stockQuantity) {
        Stock stock = stockRepository.findByWarehouseAndProduct_ProductId(warehouse, productId);
        stock.manipulateQuantity(StockReason.A, stockQuantity);
        stockRepository.save(stock);
    }

    @Transactional
    public void decreaseStock(String warehouse, int productId, int stockQuantity) {
        Stock stock = stockRepository.findByWarehouseAndProduct_ProductId(warehouse, productId);
        stock.manipulateQuantity(StockReason.B, stockQuantity);
        stockRepository.save(stock);
    }
}
