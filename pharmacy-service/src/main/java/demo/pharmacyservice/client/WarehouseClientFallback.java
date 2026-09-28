package demo.pharmacyservice.client;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WarehouseClientFallback implements WarehouseClient{
    @Override
    public Map checkStock(String medicineCode) {
        Map fallbackResponse = new HashMap<>();
        fallbackResponse.put("medicineCode", medicineCode);
        fallbackResponse.put("status", "FALLBACK");
        fallbackResponse.put("available", false);
        fallbackResponse.put("message", "Kho trung tam dang bao tri/qua tai. Vui long kiem tra kho local!");
        return fallbackResponse;
    }
}
