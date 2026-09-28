package demo.pharmacyservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(
        name = "warehouse-service",
        url = "http://localhost:8081"
)
public interface WarehouseClient {
    @GetMapping("api/v1/warehouses/check-stock")
    Map checkStock(@RequestParam("medicineName") String medicineCode);
}
