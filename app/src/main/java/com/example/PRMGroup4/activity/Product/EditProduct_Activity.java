package com.example.PRMGroup4.activity.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.adapter.Product_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Product;

import java.util.ArrayList;

public class EditProduct_Activity extends AppCompatActivity {
    Database database;



    ArrayList<Product> mangBS;
    Product_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_san_pham);
        mangBS = new ArrayList<>();

        adapter = new Product_Adapter(this, mangBS, true);

        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS sanpham(masp INTEGER PRIMARY KEY AUTOINCREMENT, tensp NVARCHAR(200),dongia FLOAT,mota TEXT,ghichu TEXT,soluongkho INTEGER,maso INTEGER , anh BLOB)");

    }
}