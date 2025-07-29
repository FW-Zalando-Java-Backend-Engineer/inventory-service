package com.example.inventoryService.repository;

import com.example.inventoryService.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.Optional;
/**
 * Repository interface for Inventory documents.
 */
public interface InventoryRepository extends MongoRepository<Inventory, String> {
    /**
     * Find inventory entry by productId.
     */
    Optional<Inventory> findByProductId(Long productId);
}
