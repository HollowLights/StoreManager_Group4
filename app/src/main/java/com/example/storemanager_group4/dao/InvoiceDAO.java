package com.example.storemanager_group4.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.storemanager_group4.database.DbHelper;
import com.example.storemanager_group4.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    private DbHelper dbHelper;

    public InvoiceDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long insert(Invoice invoice) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Invoice.COLUMN_ID_CUSTOMER, invoice.getCustomerId());
        values.put(Invoice.COLUMN_STATUS, invoice.getStatus());
        values.put(Invoice.COLUMN_DATE, invoice.getDate());

        long id = db.insert(Invoice.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public Invoice getInvoiceById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Invoice.TABLE_NAME,
                null, Invoice.COLUMN_ID_INVOICE + "=?",
                new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.COLUMN_ID_INVOICE)));
                invoice.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.COLUMN_ID_CUSTOMER)));
                invoice.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.COLUMN_STATUS)));
                invoice.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Invoice.COLUMN_DATE)));
                cursor.close();
                db.close();
                return invoice;
            }
            cursor.close();
        }
        db.close();
        return null;
    }

    public List<Invoice> getAllInvoices() {
        List<Invoice> invoices = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Invoice.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.COLUMN_ID_INVOICE)));
                invoice.setCustomerId(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.COLUMN_ID_CUSTOMER)));
                invoice.setStatus(cursor.getInt(cursor.getColumnIndexOrThrow(Invoice.COLUMN_STATUS)));
                invoice.setDate(cursor.getString(cursor.getColumnIndexOrThrow(Invoice.COLUMN_DATE)));
                invoices.add(invoice);
            }
            cursor.close();
        }
        db.close();
        return invoices;
    }

    public int update(Invoice invoice) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Invoice.COLUMN_STATUS, invoice.getStatus());
        values.put(Invoice.COLUMN_DATE, invoice.getDate());

        int rowsAffected = db.update(Invoice.TABLE_NAME, values,
                Invoice.COLUMN_ID_INVOICE + "=?", new String[]{String.valueOf(invoice.getInvoiceId())});
        db.close();
        return rowsAffected;
    }

    public void delete(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Invoice.TABLE_NAME, Invoice.COLUMN_ID_INVOICE + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
