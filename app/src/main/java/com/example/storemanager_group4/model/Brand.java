package com.example.storemanager_group4.model;

public class Brand {
    // Database table and column names
    public static final String TABLE_NAME = "Brand";
    public static final String COLUMN_ID_BRAND = "id";
    public static final String COLUMN_NAME_BRAND = "name";
    public static final String COLUMN_IMAGE = "image";

    private int brandId;
    private String brandName;
    private byte[] image;

    // Constructor
    public Brand() {
    }

    // Getters and Setters
    public int getId() {
        return brandId;
    }

    public void setId(int id) {
        this.brandId = id;
    }

    public String getName() {
        return brandName;
    }

    public void setName(String name) {
        this.brandName = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
