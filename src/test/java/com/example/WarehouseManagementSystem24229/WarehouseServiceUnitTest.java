package com.example.WarehouseManagementSystem24229;

import com.example.WarehouseManagementSystem24229.model.Warehouse;
import com.example.WarehouseManagementSystem24229.service.WarehouseServiceInterface;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarehouseServiceUnitTest {

    @Mock
    private WarehouseServiceInterface warehouseService; // Mock the service

    private WarehouseServiceUnitTest() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveItem() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setItemCode("1");
        warehouse.setItemName("Rice");
        warehouse.setType("Cereals");
        warehouse.setQuantity(500);
        warehouse.setPrice(BigDecimal.valueOf(1200));
        warehouse.setDateAdded(LocalDate.of(2023, 2, 1));
        warehouse.setDateUpdated(LocalDate.of(2023, 12, 1));

        when(warehouseService.warehouseItemSave(any(Warehouse.class))).thenReturn(warehouse);

        // Act
        Warehouse savedWarehouse = warehouseService.warehouseItemSave(warehouse);

        // Assert
        assertNotNull(savedWarehouse);
        assertEquals("1", savedWarehouse.getItemCode());
        verify(warehouseService, times(1)).warehouseItemSave(any(Warehouse.class));
    }

    @Test
    void testFindItemByCode() {
        // Arrange
        Warehouse warehouse = new Warehouse();
        warehouse.setItemCode("1");
        warehouse.setItemName("Rice");

        when(warehouseService.findWarehouseItemByCode("1")).thenReturn(warehouse);

        // Act
        Warehouse foundWarehouse = warehouseService.findWarehouseItemByCode("1");

        // Assert
        assertNotNull(foundWarehouse);
        assertEquals("Rice", foundWarehouse.getItemName());
        verify(warehouseService, times(1)).findWarehouseItemByCode("1");
    }

    @Test
    void testFindAllItems() {
        // Arrange
        Warehouse item1 = new Warehouse();
        item1.setItemCode("1");
        item1.setItemName("Rice");

        Warehouse item2 = new Warehouse();
        item2.setItemCode("2");
        item2.setItemName("Sugar");

        List<Warehouse> warehouseList = Arrays.asList(item1, item2);

        when(warehouseService.findAllWarehouseItems()).thenReturn(warehouseList);

        // Act
        List<Warehouse> items = warehouseService.findAllWarehouseItems();

        // Assert
        assertNotNull(items);
        assertEquals(2, items.size());
        verify(warehouseService, times(1)).findAllWarehouseItems();
    }

    @Test
    void testUpdateItem() {
        // Arrange
        Warehouse updatedWarehouse = new Warehouse();
        updatedWarehouse.setItemCode("1");
        updatedWarehouse.setItemName("Beans");

        when(warehouseService.updatedWarehouseItem("1", "Beans")).thenReturn(updatedWarehouse);

        // Act
        Warehouse result = warehouseService.updatedWarehouseItem("1", "Beans");

        // Assert
        assertNotNull(result);
        assertEquals("Beans", result.getItemName());
        verify(warehouseService, times(1)).updatedWarehouseItem("1", "Beans");
    }

    @Test
    void testDeleteItem() {
        // Arrange
        when(warehouseService.deleteWarehouseItem("1")).thenReturn(true);

        // Act
        boolean isDeleted = warehouseService.deleteWarehouseItem("1");

        // Assert
        assertTrue(isDeleted);
        verify(warehouseService, times(1)).deleteWarehouseItem("1");
    }
}
