package com.afalenkin.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
