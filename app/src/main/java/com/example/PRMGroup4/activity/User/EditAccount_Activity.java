package com.example.PRMGroup4.activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.adapter.Account_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Account;

import java.util.ArrayList;

public class EditAccount_Activity extends AppCompatActivity {
    Database database;


    ArrayList<Account> mangTK;
    Account_Adapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_tai_khoan);


        mangTK = new ArrayList<>();
        adapter = new Account_Adapter(getApplicationContext(), R.layout.ds_taikhoan, mangTK);
//        lv.setAdapter(adapter);
        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS taikhoan(tendn VARCHAR(20) PRIMARY KEY, matkhau VARCHAR(50), quyen VARCHAR(50))");

    }
}