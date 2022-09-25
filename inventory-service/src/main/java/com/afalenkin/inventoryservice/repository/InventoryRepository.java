package com.afalenkin.inventoryservice.repository;

import com.afalenkin.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByCode(String code);

    List<Inventory> findByCodeIn(List<String> codes);
}
