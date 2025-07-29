package com.example.inventoryService.controller;

import com.example.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing inventory stock.
 */
@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService service;

    /**
     * Get quantity for a specific product.
     */
    @GetMapping("/{productId}")
    public int getQuantity(@PathVariable Long productId) {
        return service.getQuantity(productId);
    }

    /**
     * Check if a product is in stock.
     */
    @GetMapping("/{productId}/in-stock")
    public boolean isInStock(@PathVariable Long productId) {
        return service.isInStock(productId);
    }

    /**
     * Create or update inventory for a product.
     */
    @PostMapping
    public void createOrUpdate(@RequestBody InventoryRequest request) {
        service.setStock(request.productId(), request.quantity());
    }

    /**
     * DTO class for incoming inventory updates.
     */
    public record InventoryRequest(Long productId, int quantity) {}

}
