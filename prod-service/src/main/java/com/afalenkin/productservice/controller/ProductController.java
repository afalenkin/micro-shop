package com.afalenkin.productservice.controller;

import com.afalenkin.productservice.dto.ProductRequest;
import com.afalenkin.productservice.dto.ProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface ProductController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody ProductRequest productRequest);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<ProductResponse> getAll();
}
