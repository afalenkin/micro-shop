package com.afalenkin.ordersservice.repository;

import com.afalenkin.ordersservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
