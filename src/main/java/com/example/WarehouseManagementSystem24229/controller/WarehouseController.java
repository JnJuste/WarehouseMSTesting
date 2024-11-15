package com.example.WarehouseManagementSystem24229.controller;

import com.example.WarehouseManagementSystem24229.model.Warehouse;
import com.example.WarehouseManagementSystem24229.service.WarehouseServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseServiceInterface warehouseServiceInterface;

    // 1. POST Method (Save new Item Info)
    @PostMapping("/create")
    public ResponseEntity<Warehouse> saveItem(
            @RequestBody Warehouse warehouse){
        Warehouse savedWarehouse = warehouseServiceInterface.warehouseItemSave(warehouse);
        return new ResponseEntity<>(savedWarehouse, HttpStatus.CREATED);
    }

    // 2. GET Method (Read Item By ID)
    @GetMapping("/select/{id}")
    public ResponseEntity<Warehouse> getItemById(@PathVariable("id") String id) {
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode(String.valueOf(id));
        if (warehouse != null) {
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 3.Get Method
    @GetMapping("/select")
    public ResponseEntity<List<Warehouse>> getAll() {
        List<Warehouse> warehouses = warehouseServiceInterface.findAllWarehouseItems();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    // 4. PUT Method (To Update Ticket Information By ID)
    @PutMapping("/update/{id}")
    public ResponseEntity<Warehouse> updateTicket(@PathVariable("id") String id, @RequestBody Warehouse warehouse) {
        Warehouse existingItem = warehouseServiceInterface.findWarehouseItemByCode(id);
        if(existingItem != null){
            Warehouse updatedItem = new Warehouse();
            updatedItem.setItemCode(id);
            updatedItem.setItemName(warehouse.getItemName());
            updatedItem.setType(warehouse.getType());
            updatedItem.setQuantity(warehouse.getQuantity());
            updatedItem.setPrice(warehouse.getPrice());
            updatedItem.setDateAdded(warehouse.getDateAdded());
            updatedItem.setDateUpdated(warehouse.getDateUpdated());
            return new ResponseEntity<>(warehouseServiceInterface.warehouseItemSave(updatedItem), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // 5. DELETE Method (Delete Item By ID)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Integer id) {
        try {
            warehouseServiceInterface.deleteWarehouseItem(String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
