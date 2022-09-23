package com.afakenkin.productservice.testData;

import com.afakenkin.productservice.dto.ProductRequest;
import com.afakenkin.productservice.model.Product;

import java.math.BigDecimal;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class ProductTestData {

    public static final String PRODUCT_NAME = "test name";
    public static final String PRODUCT_DESC = "test description";
    public static final BigDecimal PRODUCT_PRICE = BigDecimal.valueOf(100);

    public static ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name(PRODUCT_NAME)
                .description(PRODUCT_DESC)
                .price(PRODUCT_PRICE)
                .build();
    }

    public static Product getProduct() {
        return Product.builder()
                .name(PRODUCT_NAME)
                .description(PRODUCT_DESC)
                .price(PRODUCT_PRICE)
                .build();
    }

}
