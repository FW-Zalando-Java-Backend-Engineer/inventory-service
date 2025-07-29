package com.example.inventoryService.service;

import com.example.inventoryService.model.Inventory;
import com.example.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * Business logic for managing inventory.
 */

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repo;

    /**
     * Get current quantity in stock for a given product.
     */
    public int getQuantity(Long productId) {
        return repo.findByProductId(productId)
                .map(Inventory::getQuantity)
                .orElse(0);
    }

    /**
     * Check if the product is in stock (quantity > 0).
     */
    public boolean isInStock(Long productId) {
        return getQuantity(productId) > 0;
    }

    /**
     * Create new or update existing inventory record.
     */
    public void setStock(Long productId, int qty) {
        // Try to find existing inventory
        Inventory inv = repo.findByProductId(productId)
                .orElse(
                        new Inventory(null, productId, 0)
                );
        // id = null for new document, MongoDB will auto-generate an _id if not provided (i.e. for new records).

        // Set or update quantity
        inv.setQuantity(qty);

        // Save to MongoDB (insert or update)
        repo.save(inv);

    }
}
