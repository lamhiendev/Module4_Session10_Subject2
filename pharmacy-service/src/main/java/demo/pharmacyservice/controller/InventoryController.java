package demo.pharmacyservice.controller;
import demo.pharmacyservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/check")
    public ResponseEntity checkStock(@RequestParam String medicineCode) {
        Map result = inventoryService.checkStock(medicineCode);
        return ResponseEntity.ok(result);
    }
}