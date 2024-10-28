package com.example.storemanager_group4.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MobileManager";
    public static final int VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create User table
        String createTableUser = "CREATE TABLE User(" +
                "userId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName TEXT NOT NULL," +
                "phone TEXT NOT NULL," +
                "address TEXT," +
                "email TEXT NOT NULL UNIQUE,"+
                "dob DATE," +
                "password TEXT NOT NULL," +
                "role INTEGER," +
                "image BLOB)";
        db.execSQL(createTableUser);

        // Create Brand table
        String createTableBrand = "CREATE TABLE Brand(" +
                "brandId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "brandName TEXT NOT NULL," +
                "image BLOB)";
        db.execSQL(createTableBrand);

        // Create Product table
        String createTableProduct = "CREATE TABLE Product(" +
                "productId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "brandId INTEGER NOT NULL REFERENCES Brand(brandId) ON DELETE CASCADE ON UPDATE CASCADE," +
                "productName TEXT NOT NULL," +
                "image BLOB," +
                "price INTEGER," +
                "status INTEGER," +
                "description TEXT)";
        db.execSQL(createTableProduct);

        // Create ProductAttributes table
        String createTableProductAttributes = "CREATE TABLE ProductAttributes(" +
                "attributeId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "productId TEXT NOT NULL REFERENCES Product(productId) ON DELETE CASCADE ON UPDATE CASCADE," +
                "memory TEXT," +
                "RAM TEXT," +
                "chipset TEXT," +
                "operatingSystem TEXT," +
                "screen TEXT," +
                "batteryCapacity TEXT," +
                "chargingPort TEXT," +
                "accessoryType TEXT)";
        db.execSQL(createTableProductAttributes);

        // Create Invoice table
        String createTableInvoice = "CREATE TABLE Invoice(" +
                "invoiceId TEXT NOT NULL UNIQUE PRIMARY KEY," +
                "customerId INTEGER REFERENCES Customer(customerId) ON DELETE CASCADE ON UPDATE CASCADE," +
                "status INTEGER NOT NULL," +
                "date DATE NOT NULL)";
        db.execSQL(createTableInvoice);

        // Create InvoiceDetails table
        String createTableInvoiceDetails = "CREATE TABLE InvoiceDetails(" +
                "invoiceDetailId INTEGER PRIMARY KEY AUTOINCREMENT," +
                "invoiceId INTEGER NOT NULL REFERENCES Invoice(invoiceId) ON DELETE CASCADE ON UPDATE CASCADE," +
                "productId INTEGER NOT NULL REFERENCES Product(productId) ON DELETE CASCADE ON UPDATE CASCADE," +
                "quantity INTEGER NOT NULL," +
                "discount INTEGER," +
                "unitPrice INTEGER NOT NULL," +
                "warranty INTEGER)";
        db.execSQL(createTableInvoiceDetails);

        // Seed data
        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop tables when updating VERSION
        db.execSQL("DROP TABLE IF EXISTS Employee");
        db.execSQL("DROP TABLE IF EXISTS Customer");
        db.execSQL("DROP TABLE IF EXISTS Brand");
        db.execSQL("DROP TABLE IF EXISTS Product");
        db.execSQL("DROP TABLE IF EXISTS ProductAttributes");
        db.execSQL("DROP TABLE IF EXISTS Invoice");
        db.execSQL("DROP TABLE IF EXISTS InvoiceDetails");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys = ON");
        }
    }

    public void seedData(SQLiteDatabase db) {
        // Insert User data
        db.execSQL("INSERT INTO User (fullName, phone, address, dob, password, role, image, email) " +
                "VALUES ('John Doe', '123456789', '123 Elm Street', '1990-01-01', 'admin', 3, null, 'argentorium@gmail.com'), " +
                "('Jane Smith', '987654321', '456 Oak Avenue', '1985-02-02', 'manager1', 2, null, 'nhuulaanh@gmail.com\n'), " +
                "('Emily White', '123789456', '10 Main St', null, 'manager2', 2, null, 'argentorium1@gmail.com'), " +
                "('Frank Green', '234567890', '20 Center St', null, 'guest1', 1, null, 'thinhnthe171628@fpt.edu.vn'), " +
                "('Grace Black', '345678901', '30 Broad St', null, 'guest2', 1, null, 'argentorium2@gmail.com')");

        // Insert Brand data
        db.execSQL("INSERT INTO Brand (brandName, image) " +
                "VALUES ('Samsung', null), " +
                "('Apple', null), " +
                "('Xiaomi', null), " +
                "('OnePlus', null), " +
                "('Huawei', null)");

        // Insert Product data
        db.execSQL("INSERT INTO Product (brandId, productName, image, price, status, description) " +
                "VALUES (1, 'Samsung Galaxy S21', null, 99999, 1, 'Latest model'), " +
                "(2, 'iPhone 13', null, 109999, 1, 'Latest iPhone'), " +
                "(3, 'Xiaomi Mi 11', null, 79999, 1, 'Affordable and powerful'), " +
                "(4, 'OnePlus 9 Pro', null, 89999, 1, 'Flagship killer'), " +
                "(5, 'Huawei P40', null, 89999, 1, 'Great camera')");

        // Insert ProductAttributes data
        db.execSQL("INSERT INTO ProductAttributes (productId, memory, RAM, chipset, operatingSystem, screen, batteryCapacity, chargingPort, accessoryType) " +
                "VALUES (1, '128GB', '8GB', 'Exynos 2100', 'Android 11', '6.2 inch', '4000mAh', 'USB-C', 'Charger'), " +
                "(2, '256GB', '6GB', 'A15 Bionic', 'iOS 15', '6.1 inch', '3240mAh', 'Lightning', 'Charger'), " +
                "(3, '128GB', '8GB', 'Snapdragon 888', 'MIUI 12', '6.81 inch', '4600mAh', 'USB-C', 'Charger'), " +
                "(4, '256GB', '12GB', 'Snapdragon 888', 'OxygenOS 11', '6.7 inch', '4500mAh', 'USB-C', 'Charger'), " +
                "(5, '128GB', '8GB', 'Kirin 990', 'Android 10', '6.58 inch', '4200mAh', 'USB-C', 'Charger')");

        // Insert Invoice data
        db.execSQL("INSERT INTO Invoice (invoiceId, customerId, status, date) " +
                "VALUES ('INV001', 1, 1, '2024-10-24'), " +
                "('INV002', 2, 1, '2024-10-25'), " +
                "('INV003', 3, 1, '2024-10-26'), " +
                "('INV004', 4, 1, '2024-10-27'), " +
                "('INV005', 5, 1, '2024-10-28')");

        // Insert InvoiceDetails data
        db.execSQL("INSERT INTO InvoiceDetails (invoiceId, productId, quantity, discount, unitPrice, warranty) " +
                "VALUES ('INV001', 1, 1, 10, 89999, 12), " +
                "('INV002', 2, 1, 5, 104999, 24), " +
                "('INV003', 3, 2, 15, 67999, 12), " +
                "('INV004', 4, 1, 8, 82999, 18), " +
                "('INV005', 5, 1, 0, 89999, 12)");
    }

}
