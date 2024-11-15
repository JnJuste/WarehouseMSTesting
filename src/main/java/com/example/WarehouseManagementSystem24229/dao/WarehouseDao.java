package com.example.WarehouseManagementSystem24229.dao;

import com.example.WarehouseManagementSystem24229.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseDao extends JpaRepository<Warehouse, String> {
}
