package com.example.WarehouseManagementSystem24229.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @Column
    private String itemCode;
    @Column
    private String itemName;
    @Column
    private String type;
    @Column
    private Integer quantity;
    @Column
    private BigDecimal price;
    @Column
    private LocalDate dateAdded;
    @Column
    private LocalDate dateUpdated;

    public Warehouse() {
    }

    public Warehouse(String itemCode, String itemName, String type, Integer quantity, BigDecimal price, LocalDate dateAdded, LocalDate dateUpdated) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
