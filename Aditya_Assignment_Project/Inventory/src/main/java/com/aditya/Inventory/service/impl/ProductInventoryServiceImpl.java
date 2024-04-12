package com.aditya.Inventory.service.impl;

import com.aditya.Inventory.entity.ProductInventory;
import com.aditya.Inventory.repository.ProductInventoryRepository;
import com.aditya.Inventory.service.ProductInventoryService;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {
    @Autowired
    ProductInventoryRepository productInventoryRepository;
    @Override
    public List<ProductInventory> getAllProductInventories() {
        return productInventoryRepository.findAll();
    }
    @Override
    public ProductInventory getProductInventoryById(Long id) {
        //return productInventoryRepository.findById(id).get();
        ProductInventory productInventory= productInventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return productInventory;
    }

    @Override
    public ProductInventory createProductInventory(ProductInventory productInventory) {
        return productInventoryRepository.save(productInventory);
    }
}
