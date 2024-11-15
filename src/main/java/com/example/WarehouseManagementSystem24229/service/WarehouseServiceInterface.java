package com.example.WarehouseManagementSystem24229.service;

import com.example.WarehouseManagementSystem24229.model.Warehouse;


import java.util.List;

public interface WarehouseServiceInterface {
    Warehouse warehouseItemSave(Warehouse warehouse);
    Warehouse findWarehouseItemByCode(String id);
    List<Warehouse> findAllWarehouseItems();

    Warehouse updatedWarehouseItem(String id , String itemNa);
    boolean deleteWarehouseItem(String id);
}
