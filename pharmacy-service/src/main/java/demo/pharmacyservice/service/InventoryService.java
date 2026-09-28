package demo.pharmacyservice.service;

import demo.pharmacyservice.client.WarehouseClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    @Autowired
    private WarehouseClient warehouseClient;

    @CircuitBreaker(name = "warehouseCB", fallbackMethod = "checkStockFallback")
    public Map checkStock(String medicineCode) {
        logger.info("Dang kiem tra ton kho tai kho tong cho ma thuoc: {}", medicineCode);
        return warehouseClient.checkStock(medicineCode);
    }

    public Map checkStockFallback(String medicineCode, Exception e) {
        logger.warn("Kich hoat Fallback do kho tong gap su co. Ly do: {}", e.getMessage());

        Map response = new LinkedHashMap<>();
        response.put("medicineCode", medicineCode);
        response.put("isCentralWarehouseConnected", false);
        response.put("useLocalStock", true);


        response.put("message", "Không thể kết nối kho tổng. Hệ thống sẽ sử dụng dữ liệu tồn kho cục bộ để tiếp tục giao dịch");


        response.put("localStockQuantity", getLocalStock(medicineCode));
        response.put("allowTransaction", true);

        return response;
    }
    private int getLocalStock(String medicineCode) {
        return 50;
    }
}
