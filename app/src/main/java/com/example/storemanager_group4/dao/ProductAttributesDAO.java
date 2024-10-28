package com.example.storemanager_group4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager_group4.database.DbHelper;

import com.example.storemanager_group4.model.ProductAttributes;

public class ProductAttributesDAO {
    private DbHelper dbHelper;

    public ProductAttributesDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(ProductAttributes attributes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductAttributes.COLUMN_ID_PRODUCT, attributes.getProductId());
        values.put(ProductAttributes.COLUMN_MEMORY, attributes.getMemory());
        values.put(ProductAttributes.COLUMN_RAM, attributes.getRAM());
        values.put(ProductAttributes.COLUMN_CHIPSET, attributes.getChipset());
        values.put(ProductAttributes.COLUMN_OPERATING_SYSTEM, attributes.getOperatingSystem());
        values.put(ProductAttributes.COLUMN_SCREEN, attributes.getScreen());
        values.put(ProductAttributes.COLUMN_BATTERY_CAPACITY, attributes.getBatteryCapacity());
        values.put(ProductAttributes.COLUMN_CHARGING_PORT, attributes.getChargingPort());
        values.put(ProductAttributes.COLUMN_ACCESSORY_TYPE, attributes.getAccessoryType());

        long id = db.insert(ProductAttributes.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public ProductAttributes getAttributesByProductId(int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(ProductAttributes.TABLE_NAME,
                null, ProductAttributes.COLUMN_ID_PRODUCT + "=?",
                new String[]{String.valueOf(productId)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                ProductAttributes attributes = new ProductAttributes();
                attributes.setAttributeId(cursor.getInt(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_ID_ATTRIBUTE)));
                attributes.setProductId(cursor.getInt(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_ID_PRODUCT)));
                attributes.setMemory(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_MEMORY)));
                attributes.setRAM(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_RAM)));
                attributes.setChipset(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_CHIPSET)));
                attributes.setOperatingSystem(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_OPERATING_SYSTEM)));
                attributes.setScreen(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_SCREEN)));
                attributes.setBatteryCapacity(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_BATTERY_CAPACITY)));
                attributes.setChargingPort(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_CHARGING_PORT)));
                attributes.setAccessoryType(cursor.getString(cursor.getColumnIndexOrThrow(ProductAttributes.COLUMN_ACCESSORY_TYPE)));
                cursor.close();
                db.close();
                return attributes;
            }
            cursor.close();
        }
        db.close();
        return null;
    }

    public int update(ProductAttributes attributes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductAttributes.COLUMN_MEMORY, attributes.getMemory());
        values.put(ProductAttributes.COLUMN_RAM, attributes.getRAM());
        values.put(ProductAttributes.COLUMN_CHIPSET, attributes.getChipset());
        values.put(ProductAttributes.COLUMN_OPERATING_SYSTEM, attributes.getOperatingSystem());
        values.put(ProductAttributes.COLUMN_SCREEN, attributes.getScreen());
        values.put(ProductAttributes.COLUMN_BATTERY_CAPACITY, attributes.getBatteryCapacity());
        values.put(ProductAttributes.COLUMN_CHARGING_PORT, attributes.getChargingPort());
        values.put(ProductAttributes.COLUMN_ACCESSORY_TYPE, attributes.getAccessoryType());

        int rowsAffected = db.update(ProductAttributes.TABLE_NAME, values,
                ProductAttributes.COLUMN_ID_PRODUCT + "=?", new String[]{String.valueOf(attributes.getProductId())});
        db.close();
        return rowsAffected;
    }

    public void delete(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(ProductAttributes.TABLE_NAME, ProductAttributes.COLUMN_ID_PRODUCT + "=?", new String[]{String.valueOf(productId)});
        db.close();
    }
}
