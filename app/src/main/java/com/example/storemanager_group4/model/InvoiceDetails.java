package com.example.storemanager_group4.model;

public class InvoiceDetails {
    private int invoiceDetailId;
    private String invoiceId;
    private int productId;
    private int quantity;
    private int discount;
    private int unitPrice;
    private int warranty;

    // Table and column constants
    public static final String TABLE_NAME = "InvoiceDetails";
    public static final String COLUMN_ID_INVOICE_DETAIL = "invoiceDetailId";
    public static final String COLUMN_ID_INVOICE = "invoiceId";
    public static final String COLUMN_ID_PRODUCT = "productId";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_DISCOUNT = "discount";
    public static final String COLUMN_UNIT_PRICE = "unitPrice";
    public static final String COLUMN_WARRANTY = "warranty";

    // Getters and Setters
    public int getInvoiceDetailId() { return invoiceDetailId; }
    public void setInvoiceDetailId(int invoiceDetailId) { this.invoiceDetailId = invoiceDetailId; }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getDiscount() { return discount; }
    public void setDiscount(int discount) { this.discount = discount; }

    public int getUnitPrice() { return unitPrice; }
    public void setUnitPrice(int unitPrice) { this.unitPrice = unitPrice; }

    public int getWarranty() { return warranty; }
    public void setWarranty(int warranty) { this.warranty = warranty; }
}
