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

    // 1. POST Method (Save new Item Info - Positive)
    @PostMapping("/create")
    public ResponseEntity<Warehouse> saveItem(@RequestBody Warehouse warehouse) {
        Warehouse savedWarehouse = warehouseServiceInterface.warehouseItemSave(warehouse);
        return new ResponseEntity<>(savedWarehouse, HttpStatus.CREATED);
    }

    // 1.1. POST Method (Save new Item Info - Negative)
    @PostMapping("/create-invalid")
    public ResponseEntity<String> saveInvalidItem(@RequestBody Warehouse warehouse) {
        try {
            if (warehouse.getItemCode() == null || warehouse.getItemCode().isEmpty()) {
                return new ResponseEntity<>("Item code is missing.", HttpStatus.BAD_REQUEST);
            }
            Warehouse savedWarehouse = warehouseServiceInterface.warehouseItemSave(warehouse);
            return new ResponseEntity<>("Item saved successfully.", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to save the item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 2. GET Method (Read Item By ID - Positive)
    @GetMapping("/select/{id}")
    public ResponseEntity<Warehouse> getItemById(@PathVariable("id") String id) {
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode(id);
        if (warehouse != null) {
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 2.1. GET Method (Read Item By ID - Negative)
    @GetMapping("/select-invalid/{id}")
    public ResponseEntity<String> getInvalidItemById(@PathVariable("id") String id) {
        try {
            Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode(id);
            if (warehouse == null) {
                return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Item found successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error while fetching the item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 3. GET Method (Retrieve All Items - Positive)
    @GetMapping("/select")
    public ResponseEntity<List<Warehouse>> getAll() {
        List<Warehouse> warehouses = warehouseServiceInterface.findAllWarehouseItems();
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    // 3.1. GET Method (Retrieve All Items - Negative)
    @GetMapping("/select-empty")
    public ResponseEntity<String> getEmptyItems() {
        List<Warehouse> warehouses = warehouseServiceInterface.findAllWarehouseItems();
        if (warehouses == null || warehouses.isEmpty()) {
            return new ResponseEntity<>("No items available in the warehouse.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Items retrieved successfully.", HttpStatus.OK);
    }

    // 4. PUT Method (To Update Ticket Information By ID - Positive)
    @PutMapping("/update/{id}")
    public ResponseEntity<Warehouse> updateTicket(@PathVariable("id") String id, @RequestBody Warehouse warehouse) {
        Warehouse existingItem = warehouseServiceInterface.findWarehouseItemByCode(id);
        if (existingItem != null) {
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

    // 4.1. PUT Method (To Update Ticket Information By ID - Negative)
    @PutMapping("/update-invalid/{id}")
    public ResponseEntity<String> updateInvalidTicket(@PathVariable("id") String id, @RequestBody Warehouse warehouse) {
        try {
            Warehouse existingItem = warehouseServiceInterface.findWarehouseItemByCode(id);
            if (existingItem == null) {
                return new ResponseEntity<>("Item not found for update.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Item updated successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error while updating the item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5. DELETE Method (Delete Item By ID - Positive)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable("id") Integer id) {
        try {
            warehouseServiceInterface.deleteWarehouseItem(String.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 5.1. DELETE Method (Delete Item By ID - Negative)
    @DeleteMapping("/delete-invalid/{id}")
    public ResponseEntity<String> deleteInvalidTicket(@PathVariable("id") Integer id) {
        try {
            boolean isDeleted = warehouseServiceInterface.deleteWarehouseItem(String.valueOf(id));
            if (!isDeleted) {
                return new ResponseEntity<>("Item not found for deletion.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Item deleted successfully.", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error while deleting the item.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
