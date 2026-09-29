package demo.inventoryservice.config;

import demo.inventoryservice.event.OrderEvent;
import demo.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final InventoryService inventoryService;

    @KafkaListener(
            topics = "medicine-stock-events",
            groupId = "inventory-service-group"
    )
    public void consume(OrderEvent event) {

        System.out.println("========== KAFKA EVENT ==========");
        System.out.println("Medicine ID: " + event.getMedicineId());
        System.out.println("Quantity: " + event.getQuantity());

        inventoryService.processOrder(event);
    }
}
