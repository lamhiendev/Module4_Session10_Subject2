package demo.pharmacyservice.service;

import demo.pharmacyservice.client.WarehouseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private WarehouseClient warehouseClient;

    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkStockFallback")
    public Map checkStock(String medicineCode) {
        logger.info("Dang goi FeignClient sang Warehouse Service cho ma thuoc: {}", medicineCode);
        return warehouseClient.checkStock(medicineCode);
    }

    public Map checkStockFallback(String medicineCode, Throwable throwable) {
        logger.warn("Circuit Breaker hoat dong qua FeignClient! Ly do: {}", throwable.getMessage());

        Map fallbackResponse = new HashMap<>();
        fallbackResponse.put("medicineCode", medicineCode);
        fallbackResponse.put("status", "FALLBACK");
        fallbackResponse.put("available", false);
        fallbackResponse.put("message", "He thong kho trung tam dang bao tri/qua tai. Vui long kiem tra kho local!");
        fallbackResponse.put("error", throwable.getClass().getSimpleName());

        return fallbackResponse;
    }
}
