package com.example.storemanager_group4.model;

public class ProductAttributes {
    private int attributeId;
    private int productId;
    private String memory;
    private String RAM;
    private String chipset;
    private String operatingSystem;
    private String screen;
    private String batteryCapacity;
    private String chargingPort;
    private String accessoryType;

    // Table and column constants
    public static final String TABLE_NAME = "ProductAttributes";
    public static final String COLUMN_ID_ATTRIBUTE = "attributeId";
    public static final String COLUMN_ID_PRODUCT = "productId";
    public static final String COLUMN_MEMORY = "memory";
    public static final String COLUMN_RAM = "RAM";
    public static final String COLUMN_CHIPSET = "chipset";
    public static final String COLUMN_OPERATING_SYSTEM = "operatingSystem";
    public static final String COLUMN_SCREEN = "screen";
    public static final String COLUMN_BATTERY_CAPACITY = "batteryCapacity";
    public static final String COLUMN_CHARGING_PORT = "chargingPort";
    public static final String COLUMN_ACCESSORY_TYPE = "accessoryType";

    // Getters and Setters
    public int getAttributeId() { return attributeId; }
    public void setAttributeId(int attributeId) { this.attributeId = attributeId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getMemory() { return memory; }
    public void setMemory(String memory) { this.memory = memory; }

    public String getRAM() { return RAM; }
    public void setRAM(String RAM) { this.RAM = RAM; }

    public String getChipset() { return chipset; }
    public void setChipset(String chipset) { this.chipset = chipset; }

    public String getOperatingSystem() { return operatingSystem; }
    public void setOperatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; }

    public String getScreen() { return screen; }
    public void setScreen(String screen) { this.screen = screen; }

    public String getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(String batteryCapacity) { this.batteryCapacity = batteryCapacity; }

    public String getChargingPort() { return chargingPort; }
    public void setChargingPort(String chargingPort) { this.chargingPort = chargingPort; }

    public String getAccessoryType() { return accessoryType; }
    public void setAccessoryType(String accessoryType) { this.accessoryType = accessoryType; }
}
