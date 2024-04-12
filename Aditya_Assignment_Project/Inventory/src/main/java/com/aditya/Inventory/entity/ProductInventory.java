package com.aditya.Inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductInventory {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private Long pid;
    private String pname;
    private Long qty;
    private double price;

}
