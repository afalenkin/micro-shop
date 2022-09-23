package com.afalenkin.productservice.repository;

import com.afalenkin.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}
