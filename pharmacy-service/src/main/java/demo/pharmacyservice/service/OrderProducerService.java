package demo.pharmacyservice.service;

import demo.pharmacyservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderProducerService {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducerService.class);
    private static final String TOPIC = "medicine-stock-events";

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public OrderEvent sendOrderEvent(String medicineId, int quantity) {
        // 1. Khởi tạo đối tượng OrderEvent
        String orderId = "ORD-" + UUID.randomUUID().toString().substring(0, 8);
        OrderEvent orderEvent = new OrderEvent(orderId, medicineId, quantity, LocalDateTime.now());

        kafkaTemplate.send(TOPIC, orderEvent.getMedicineId(), orderEvent);

        System.out.println("Đã gửi sự kiện đơn hàng ID: " + orderEvent.getOrderId());

        return orderEvent;
    }
}