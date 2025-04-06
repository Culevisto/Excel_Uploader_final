package com.example.excelanalyzer;

import java.time.LocalDate;

public class SalesData {
    private int id;
    private String productName;
    private double price;
    private int quantity;
    private double totalSale;
    private LocalDate saleDate;

    public SalesData(int id, String productName, double price, int quantity, double totalSale, LocalDate saleDate) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.totalSale = totalSale;
        this.saleDate = saleDate;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }
}
