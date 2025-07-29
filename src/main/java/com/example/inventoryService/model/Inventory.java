package com.example.inventoryService.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Inventory document representing stock per product.
 */
@Document("inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    /** MongoDB unique document ID */
    @Id
    private String id;

    /** Product ID to which this stock belongs */
    private Long productId;

    /** Current stock quantity for the product */
    private int quantity;
}
