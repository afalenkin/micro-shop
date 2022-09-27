package com.afalenkin.notificationservice.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderPlacedEvent {
    private String orderNumber;
}
