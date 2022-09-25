package com.afalenkin.inventoryservice.service;

import com.afalenkin.inventoryservice.dto.InventoryResponse;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface InventoryService {

    boolean isInStock(String code);

    List<InventoryResponse> isInStock(List<String> codes);

}
