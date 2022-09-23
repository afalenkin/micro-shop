package com.afakenkin.productservice.service;

import com.afakenkin.productservice.dto.ProductRequest;
import com.afakenkin.productservice.dto.ProductResponse;
import com.afakenkin.productservice.model.Product;
import com.afakenkin.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl {

    private final ProductRepository repository;

    public void create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        String productId = repository.save(product).getId();
        log.info("Product {} was successfully saved", productId);
    }

    public List<ProductResponse> getAll() {
        return repository.findAll().stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    private ProductResponse map(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}