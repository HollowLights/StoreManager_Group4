package com.example.PRMGroup4.activity.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.adapter.Category_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Category;

import java.util.ArrayList;

public class EditCategory_Activity extends AppCompatActivity {
    Database database;
    ArrayList<Category> mangNSP;
    Category_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nhomsanpham);
        mangNSP = new ArrayList<>();
        adapter = new Category_Adapter(EditCategory_Activity.this, mangNSP, true);
        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS nhomsanpham(maso INTEGER PRIMARY KEY AUTOINCREMENT, tennsp NVARCHAR(200), anh BLOB)");

    }
}