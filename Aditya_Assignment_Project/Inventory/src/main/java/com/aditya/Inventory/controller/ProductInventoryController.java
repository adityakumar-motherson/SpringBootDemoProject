package com.aditya.Inventory.controller;

import com.aditya.Inventory.entity.ProductInventory;
import com.aditya.Inventory.service.ProductInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/productinventory")
@RestController
public class ProductInventoryController {
    @Autowired
    ProductInventoryService productInventoryService;
    @GetMapping
    public ResponseEntity<List<ProductInventory>> getAllProductInventories()
    {
        var data=productInventoryService.getAllProductInventories();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductInventory> getProductInventoryById(@PathVariable("id") Long id)
    {
        var data=productInventoryService.getProductInventoryById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

//    @GetMapping("/{id}/{qty}")
//    public ResponseEntity<ProductInventory> getProductInventoryQtyById(@PathVariable("id") Long id,@PathVariable("qty") Long qty)
//    {
//        var data=productInventoryService.getProductInventoryById(id);
//        var currentQty=data.getQty();
//        var newQty=currentQty-qty;
//        if(newQty>=0)
//        {
//          data.setQty(newQty);
//            productInventoryService.createProductInventory(data);
//        }
//        else
//        {
//            return new ResponseEntity<>(HttpStatus.GONE);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<ProductInventory> createProductInventory(@RequestBody ProductInventory productInventory)
    {
        var data=productInventoryService.createProductInventory(productInventory);
        return new ResponseEntity<>(data,HttpStatus.CREATED);
    }


}
