package com.aditya.Inventory.repository;

import com.aditya.Inventory.entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory,Long>
{
}
