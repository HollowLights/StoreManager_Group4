package com.example.storemanager_group4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager_group4.database.DbHelper;
import com.example.storemanager_group4.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private SQLiteDatabase database;
    private DbHelper dbHelper;

    public ProductDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
        dbHelper.close();
    }

    public long add(Product product) {
        ContentValues values = new ContentValues();
        values.put(Product.COLUMN_ID_PRODUCT, product.getProductId());
        values.put(Product.COLUMN_ID_BRAND, product.getBrandId());
        values.put(Product.COLUMN_NAME_PRODUCT, product.getProductName());
        values.put(Product.COLUMN_IMAGE, product.getImage());
        values.put(Product.COLUMN_STATUS, product.getStatus());
        values.put(Product.COLUMN_PRICE, product.getPrice());
        values.put(Product.COLUMN_DESCRIPTION, product.getDescription());
        return database.insert(Product.TABLE_NAME, null, values);
    }

    public int delete(String productId) {
        return database.delete(Product.TABLE_NAME, "productId = ?", new String[]{productId});
    }

    public int update(Product product, String productId) {
        if (product == null || productId == null) {
            return -1; // Error handling
        }
        ContentValues values = new ContentValues();
        values.put(Product.COLUMN_ID_PRODUCT, product.getProductId());
        values.put(Product.COLUMN_ID_BRAND, product.getBrandId());
        values.put(Product.COLUMN_NAME_PRODUCT, product.getProductName());
        values.put(Product.COLUMN_IMAGE, product.getImage());
        values.put(Product.COLUMN_STATUS, product.getStatus());
        values.put(Product.COLUMN_PRICE, product.getPrice());
        values.put(Product.COLUMN_DESCRIPTION, product.getDescription());
        return database.update(Product.TABLE_NAME, values, "productId = ?", new String[]{productId});
    }

    public List<Product> getAll() {
        String sql = "SELECT * FROM Product";
        return getData(sql);
    }

    public List<Product> getAllActiveProduct() {
        String sql = "SELECT * FROM Product WHERE status = 1";
        return getData(sql);
    }

    public List<Product> getAllProductName() {
        String sql = "SELECT * FROM Product ORDER BY tenSP ASC"; // Ensure 'tenSP' is defined
        return getData(sql);
    }

    public List<Product> getAllProductId() {
        String sql = "SELECT * FROM Product ORDER BY productId ASC";
        return getData(sql);
    }

    public Product getProductById(String productId) {
        String sql = "SELECT * FROM Product WHERE productId = ?";
        List<Product> list = getData(sql, productId);
        return list.isEmpty() ? null : list.get(0);
    }

    public boolean checkProductExist(String productId) {
        String sql = "SELECT * FROM Product WHERE productId = ?";
        List<Product> list = getData(sql, productId);
        return !list.isEmpty();
    }

    public List<Product> getData(String sql, String... args) {
        List<Product> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(sql, args);
            while (cursor.moveToNext()) {
                Product product = new Product();
                product.setProductId(cursor.getInt(cursor.getColumnIndexOrThrow(Product.COLUMN_ID_PRODUCT)));
                product.setBrandId(cursor.getInt(cursor.getColumnIndexOrThrow(Product.COLUMN_ID_BRAND)));
                product.setProductName(cursor.getString(cursor.getColumnIndexOrThrow(Product.COLUMN_NAME_PRODUCT)));
                product.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(Product.COLUMN_IMAGE)));
                product.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(Product.COLUMN_STATUS)));
                product.setPrice((cursor.getInt(cursor.getColumnIndexOrThrow(Product.COLUMN_PRICE))));
                product.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(Product.COLUMN_DESCRIPTION)));
                list.add(product);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return list;
    }
}
