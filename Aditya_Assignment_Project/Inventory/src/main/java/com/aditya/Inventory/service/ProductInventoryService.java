package com.aditya.Inventory.service;

import com.aditya.Inventory.entity.ProductInventory;

import java.util.List;

public interface ProductInventoryService {
    List<ProductInventory> getAllProductInventories();
    ProductInventory getProductInventoryById(Long id);
    ProductInventory createProductInventory(ProductInventory productInventory);
}
