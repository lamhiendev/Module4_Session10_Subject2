package demo.pharmacyservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderEvent {
    private String orderId;
    private String medicineId;
    private int quantity;
    private LocalDateTime timestamp;
}