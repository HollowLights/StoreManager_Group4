package com.example.storemanager_group4.model;

public class Product {
    private int productId;
    private int brandId;
    private String productName;
    private byte[] image;
    private int price;
    private int status;
    private String description;

    // Table and column constants
    public static final String TABLE_NAME = "Product";
    public static final String COLUMN_ID_PRODUCT = "productId";
    public static final String COLUMN_ID_BRAND = "brandId";
    public static final String COLUMN_NAME_PRODUCT = "productName";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DESCRIPTION = "description";

    // Getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
