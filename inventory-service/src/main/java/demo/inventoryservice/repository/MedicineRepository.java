package demo.inventoryservice.repository;

import demo.inventoryservice.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MedicineRepository extends JpaRepository<Medicine, String> {
    @Modifying
    @Query("""
        UPDATE Medicine m
        SET m.stock = m.stock - :quantity
        WHERE m.id = :medicineId
          AND m.stock >= :quantity
    """)
    int decreaseStock(
            @Param("medicineId") String medicineId,
            @Param("quantity") Integer quantity
    );
}
