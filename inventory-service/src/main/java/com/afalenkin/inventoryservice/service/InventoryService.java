package com.afalenkin.inventoryservice.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface InventoryService {
    @Transactional(readOnly = true)
    boolean isInStock(String code);
}
