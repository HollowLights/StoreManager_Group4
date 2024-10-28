package com.example.storemanager_group4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager_group4.database.DbHelper;
import com.example.storemanager_group4.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private DbHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_FULL_NAME, user.getFullName());
        values.put(User.COLUMN_PHONE, user.getPhone());
        values.put(User.COLUMN_ADDRESS, user.getAddress());
        values.put(User.COLUMN_EMAIL, user.getEmail());
        values.put(User.COLUMN_DOB, user.getDob());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_ROLE, user.getRole());
        values.put(User.COLUMN_IMAGE, user.getImage());

        long id = db.insert(User.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public User getUserById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME,
                null, User.COLUMN_ID_USER + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                User user = new User();
                user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(User.COLUMN_ID_USER)));
                user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_FULL_NAME)));
                user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_PHONE)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_EMAIL)));
                user.setDob(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_DOB)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_PASSWORD)));
                user.setRole(cursor.getInt(cursor.getColumnIndexOrThrow(User.COLUMN_ROLE)));
                user.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(User.COLUMN_IMAGE)));
                cursor.close();
                db.close();
                return user;
            }
            cursor.close();
        }
        db.close();
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                User user = new User();
                user.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(User.COLUMN_ID_USER)));
                user.setFullName(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_FULL_NAME)));
                user.setPhone(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_PHONE)));
                user.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_ADDRESS)));
                user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_EMAIL)));
                user.setDob(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_DOB)));
                user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(User.COLUMN_PASSWORD)));
                user.setRole(cursor.getInt(cursor.getColumnIndexOrThrow(User.COLUMN_ROLE)));
                user.setImage(cursor.getBlob(cursor.getColumnIndexOrThrow(User.COLUMN_IMAGE)));
                users.add(user);
            }
            cursor.close();
        }
        db.close();
        return users;
    }

    public int update(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_FULL_NAME, user.getFullName());
        values.put(User.COLUMN_PHONE, user.getPhone());
        values.put(User.COLUMN_ADDRESS, user.getAddress());
        values.put(User.COLUMN_EMAIL, user.getAddress());
        values.put(User.COLUMN_DOB, user.getDob());
        values.put(User.COLUMN_PASSWORD, user.getPassword());
        values.put(User.COLUMN_ROLE, user.getRole());
        values.put(User.COLUMN_IMAGE, user.getImage());

        int rowsAffected = db.update(User.TABLE_NAME, values,
                User.COLUMN_ID_USER + "=?", new String[]{String.valueOf(user.getUserId())});
        db.close();
        return rowsAffected;
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE_NAME, User.COLUMN_ID_USER + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Method to authenticate user
    public boolean authenticateUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{email, password});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;  // Valid user
        } else {
            cursor.close();
            return false;  // Invalid credentials
        }
    }
}