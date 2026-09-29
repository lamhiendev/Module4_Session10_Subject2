package demo.inventoryservice.service;

import demo.inventoryservice.event.OrderEvent;
import demo.inventoryservice.repository.MedicineRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final MedicineRepository medicineRepository;

    @Transactional
    public void processOrder(OrderEvent event) {

        int updatedRows = medicineRepository.decreaseStock(
                event.getMedicineId(),
                event.getQuantity()
        );

        if (updatedRows == 0) {
            throw new RuntimeException(
                    "Không đủ tồn kho hoặc không tìm thấy thuốc: "
                            + event.getMedicineId()
            );
        }

        System.out.println(
                "Đã trừ " + event.getQuantity()
                        + " thuốc " + event.getMedicineId()
        );
    }
}
