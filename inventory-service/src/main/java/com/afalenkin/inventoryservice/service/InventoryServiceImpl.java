package com.afalenkin.inventoryservice.service;

import com.afalenkin.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    @Override
    @Transactional(readOnly = true)
    public boolean isInStock(String code) {
       return repository.findByCode(code).isPresent();
    }

}
