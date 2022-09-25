package com.afalenkin.productservice.service;

import com.afalenkin.productservice.dto.ProductRequest;
import com.afalenkin.productservice.dto.ProductResponse;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface ProductService {

    void create(ProductRequest productRequest);

    List<ProductResponse> getAll();
}
