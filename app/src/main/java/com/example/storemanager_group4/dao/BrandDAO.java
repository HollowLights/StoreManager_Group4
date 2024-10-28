package com.example.storemanager_group4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager_group4.database.DbHelper;
import com.example.storemanager_group4.model.Brand;

import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    private DbHelper dbHelper;

    public BrandDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(Brand brand) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Brand.COLUMN_NAME_BRAND, brand.getName());
        values.put(Brand.COLUMN_IMAGE, brand.getImage());

        long id = db.insert(Brand.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Brand getBrandById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Brand.TABLE_NAME,
                null, Brand.COLUMN_ID_BRAND + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Brand brand = new Brand();
                brand.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Brand.COLUMN_ID_BRAND)));
                brand.setName(cursor.getString(cursor.getColumnIndexOrThrow(Brand.COLUMN_NAME_BRAND)));
                brand.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(Brand.COLUMN_IMAGE)));
                cursor.close();
                db.close();
                return brand;
            }
            cursor.close();
        }
        db.close();
        return null;
    }

    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Brand.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Brand brand = new Brand();
                brand.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Brand.COLUMN_ID_BRAND)));
                brand.setName(cursor.getString(cursor.getColumnIndexOrThrow(Brand.COLUMN_NAME_BRAND)));
                brand.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(Brand.COLUMN_IMAGE)));
                brands.add(brand);
            }
            cursor.close();
        }
        db.close();
        return brands;
    }

    public int update(Brand brand) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Brand.COLUMN_NAME_BRAND, brand.getName());
        values.put(Brand.COLUMN_IMAGE, brand.getImage());

        int rowsAffected = db.update(Brand.TABLE_NAME, values,
                Brand.COLUMN_ID_BRAND + "=?", new String[]{String.valueOf(brand.getId())});
        db.close();
        return rowsAffected;
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Brand.TABLE_NAME, Brand.COLUMN_ID_BRAND + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
