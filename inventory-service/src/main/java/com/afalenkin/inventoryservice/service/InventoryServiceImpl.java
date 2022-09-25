package com.afalenkin.inventoryservice.service;

import com.afalenkin.inventoryservice.dto.InventoryResponse;
import com.afalenkin.inventoryservice.model.Inventory;
import com.afalenkin.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    public List<InventoryResponse> isInStock(List<String> codes) {
        return repository.findByCodeIn(codes).stream()
                .map(this::toInventoryResponse)
                .toList();
    }

    private InventoryResponse toInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .code(inventory.getCode())
                .isInStock(inventoryIsPresent(inventory))
                .build();
    }

    private boolean inventoryIsPresent(Inventory inventory) {
        return Objects.nonNull(inventory.getQuantity()) && inventory.getQuantity() > 0;
    }

}
