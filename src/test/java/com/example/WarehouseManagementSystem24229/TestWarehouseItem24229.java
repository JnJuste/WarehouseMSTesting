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
    public void testSaveItemPositive() {
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
     * Negative Test to verify save functionality fails for invalid Warehouse item.
     */
    @Test
    @Order(2)
    public void testSaveItemNegative() {
        Warehouse ware = new Warehouse();
        ware.setItemCode(null); // Invalid: Item code is required
        ware.setItemName("Sugar");
        ware.setType("Sweetener");
        ware.setQuantity(200);
        ware.setPrice(BigDecimal.valueOf(800));
        ware.setDateAdded(LocalDate.of(2023, 2, 1));
        ware.setDateUpdated(LocalDate.of(2023, 12, 1));

        Exception exception = assertThrows(Exception.class, () -> {
            warehouseServiceInterface.warehouseItemSave(ware);
        });

        assertNotNull(exception, "Expected an exception when saving an item with a null code.");
    }

    /**
     * Positive Test to verify the find functionality of a Warehouse item by its code.
     */
    @Test
    @Order(3)
    public void testFindItemByCodePositive() {
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode("1");
        assertEquals("Cereals", warehouse.getType()); // Asserts that the item Type is as expected
        assertEquals("Rice", warehouse.getItemName()); // Asserts that the item Name is as expected
    }

    /**
     * Negative Test to verify the find functionality of a Warehouse item by its code.
     */
    @Test
    @Order(4)
    public void testFindItemByCodeNegative() {
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode("9999"); // Non-existent code
        assertNull(warehouse, "Expected no item to be found for a non-existent code.");
    }

    /**
     * Positive Test to verify the retrieval of all Warehouse items.
     */
    @Test
    @Order(5)
    public void testFindAllPositive() {
        List<Warehouse> warehouses = warehouseServiceInterface.findAllWarehouseItems();
        assertEquals(1, warehouses.size()); // Asserts that there is exactly one Warehouse item
    }

    /**
     * Negative Test to verify the retrieval of all Warehouse items.
     */
    @Test
    @Order(6)
    public void testFindAllNegative() {
        List<Warehouse> warehouses = warehouseServiceInterface.findAllWarehouseItems();
        assertNotEquals(0, warehouses.size(), "Expected the warehouse list to not be empty.");
    }

    /**
     * Positive Test to verify the update functionality of a Warehouse item.
     */
    @Test
    @Order(7)
    public void testUpdateItemPositive() {
        Warehouse warehouse = warehouseServiceInterface.updatedWarehouseItem("1", "Beans");
        assertEquals("Beans", warehouse.getItemName()); // Asserts that the item name was successfully updated
    }

    /**
     * Negative Test to verify the update functionality of a Warehouse item.
     */
    @Test
    @Order(8)
    public void testUpdateItemNegative() {
        Warehouse warehouse = warehouseServiceInterface.updatedWarehouseItem("9999", "Beans"); // Non-existent code
        assertNull(warehouse, "Expected no update for a non-existent item code.");
    }

    /**
     * Positive Test to verify the delete functionality of a Warehouse item.
     */
    @Test
    @Order(9)
    public void testDeleteItemPositive() {
        warehouseServiceInterface.deleteWarehouseItem("1");
        Warehouse warehouse = warehouseServiceInterface.findWarehouseItemByCode("1");

        assertNull(warehouse); // Asserts that the item has been successfully deleted
    }

    /**
     * Negative Test to verify the delete functionality of a Warehouse item.
     */
    @Test
    @Order(10)
    public void testDeleteItemNegative() {
        // Attempt to delete a non-existent item
        boolean isDeleted = warehouseServiceInterface.deleteWarehouseItem("9999"); // Assume "9999" does not exist

        // Verify that the deletion attempt returns false or an appropriate response
        assertFalse(isDeleted, "Expected deletion to fail for non-existent item code.");
    }
}
