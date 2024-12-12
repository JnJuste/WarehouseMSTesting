package com.example.WarehouseManagementSystem24229;

// Importing necessary classes and interfaces
import com.example.WarehouseManagementSystem24229.model.Warehouse;
import com.example.WarehouseManagementSystem24229.service.WarehouseServiceInterface;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the WarehouseServiceInterface implementation.
 * Ensures that the CRUD operations for Warehouse items work as expected.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestWarehouseItem24229 {

    @Autowired
    private WarehouseServiceInterface warehouseServiceInterface; // Injecting the WarehouseServiceInterface bean

    /**
     * Test to verify the save functionality of a Warehouse item.
     */
    @Test
    @Order(1)
    public void testSaveItem() {
        Warehouse ware = new Warehouse();
        ware.setItemCode("1");
        ware.setItemName("Rice");
        ware.setType("Cereals");
        ware.setQuantity(500);
        ware.setPrice(BigDecimal.valueOf(1200));
        ware.setDateAdded(LocalDate.of(2023, 2, 1));
        ware.setDateUpdated(LocalDate.of(2023, 12, 1));

        Warehouse warehouse = warehouseServiceInterface.warehouseItemSave(ware);
        assertNotNull(warehouse); // Asserts that the saved Warehouse item is not null
    }

    /**
     * Test to verify the find functionality of a Warehouse item by its code.
     */
    @Test
    @Order(2)
    public void testFindItem() {
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode("1");
        assertEquals("Rice", warehouse.getItemName()); // Asserts that the item name is as expected
    }

    /**
     * Test to verify the retrieval of all Warehouse items.
     */
    @Test
    @Order(3)
    public void testFindAll(){
        List<Warehouse> warehouses = warehouseServiceInterface.findAllWarehouseItems();
        assertEquals(1, warehouses.size()); // Asserts that there is exactly one Warehouse item
    }

    /**
     * Test to verify the update functionality of a Warehouse item.
     */
    @Test
    @Order(4)
    public void testUpdateItem() {
        Warehouse warehouse = warehouseServiceInterface.updatedWarehouseItem("1", "Beans");
        assertEquals("Beans", warehouse.getItemName()); // Asserts that the item name was successfully updated
    }

    /**
     * Test to verify the delete functionality of a Warehouse item.
     */
    @Test
    @Order(5)
    public void testDeleteBook() {
        warehouseServiceInterface.deleteWarehouseItem("1");
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode("1");

        assertNull(warehouse); // Asserts that the item has been successfully deleted
    }
}
