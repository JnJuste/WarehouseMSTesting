package com.example.WarehouseManagementSystem24229.service;

import com.example.WarehouseManagementSystem24229.dao.WarehouseDao;
import com.example.WarehouseManagementSystem24229.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class WarehouseServiceImplementation implements WarehouseServiceInterface{

    @Autowired
    private WarehouseDao warehouseDao;

    @Override
    public Warehouse warehouseItemSave(Warehouse warehouse) {
        return warehouseDao.save(warehouse);
    }

    @Override
    public Warehouse findWarehouseItemByCode(String id) {
        Warehouse warehouse = warehouseDao.findById(id).orElse(null);
        if (warehouse == null) {
            return null;
        } else {
            return warehouse;
        }
    }

    @Override
    public List<Warehouse> findAllWarehouseItems() {
        return warehouseDao.findAll();
    }

    @Override
    public Warehouse updatedWarehouseItem(
            String id, String itemNa) {
        Warehouse warehouse = warehouseDao.findById(id).orElse(null);
        if (warehouse != null) {
            warehouse.setItemName(itemNa);
            return warehouseDao.save(warehouse);
        } else{
            return null;
        }
    }

    @Override
    public boolean deleteWarehouseItem(String id) {
        Warehouse warehouse = warehouseDao.findById(id).orElse(null);
        if (warehouse != null) {
            warehouseDao.delete(warehouse);
            return true;
        }
        return false;
    }
}
