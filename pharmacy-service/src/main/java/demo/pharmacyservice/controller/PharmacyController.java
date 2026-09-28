package demo.pharmacyservice.controller;

import demo.pharmacyservice.dto.BillRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.netflix.spectator.api.Statistic.totalAmount;

@RestController
@RequestMapping("/api/v1/bill")
@RefreshScope
public class PharmacyController {
    @Value("${pharmacy.vat-rate}")
    private double vatRate;
    @PostMapping
    public ResponseEntity calculateMedicineBill(@RequestBody BillRequest billRequest){
        double baseAmount = billRequest.getAmount();
        double vatAmount = baseAmount * vatRate;
        double totalAmount = baseAmount + vatAmount;

        Map response = new LinkedHashMap<>();
        response.put("medicineName", billRequest.getMedicineName());
        response.put("baseAmount", baseAmount);
        response.put("vatRate", vatRate);
        response.put("vatAmount", vatAmount);
        response.put("totalAmount", totalAmount);

        return ResponseEntity.ok(response);
    }
}
