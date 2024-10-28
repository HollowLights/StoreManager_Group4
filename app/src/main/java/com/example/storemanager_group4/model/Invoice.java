package com.example.storemanager_group4.model;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private int status;
    private String date;

    // Table and column constants
    public static final String TABLE_NAME = "Invoice";
    public static final String COLUMN_ID_INVOICE = "invoiceId";
    public static final String COLUMN_ID_CUSTOMER = "customerId";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DATE = "date";

    // Getters and Setters
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
