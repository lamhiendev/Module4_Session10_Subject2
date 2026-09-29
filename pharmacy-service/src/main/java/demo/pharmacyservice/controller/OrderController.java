package demo.pharmacyservice.controller; // Kiểm tra đúng package

import demo.pharmacyservice.dto.OrderEvent;
import demo.pharmacyservice.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders") // <- Đường dẫn gốc 1
public class OrderController {

    @Autowired
    private OrderProducerService orderProducerService;

    @PostMapping("/checkout") // <- Đường dẫn con 2 (kết hợp lại thành /api/v1/orders/checkout)
    public ResponseEntity checkout(@RequestParam String medicineId,
                                    @RequestParam int quantity) {
        OrderEvent event = orderProducerService.sendOrderEvent(medicineId, quantity);

        Map response = new LinkedHashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "Thanh toán đơn hàng thành công!");
        response.put("data", event);

        return ResponseEntity.ok(response);
    }
}