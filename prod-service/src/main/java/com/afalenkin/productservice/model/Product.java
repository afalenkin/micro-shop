package com.afalenkin.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Аннотация @Document служит для того, чтобы указать что люъекты этого типа будут храниться в noSql базе (аналог Entity)
 */
@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@With
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

}
