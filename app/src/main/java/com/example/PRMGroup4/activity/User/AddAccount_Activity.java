package com.example.PRMGroup4.activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.adapter.Account_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Account;

import java.util.ArrayList;

public class AddAccount_Activity extends AppCompatActivity {
    Database database;


    ArrayList<Account> mangTK;
    Account_Adapter adapter;

    RadioButton admin;
    RadioButton user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tai_khoan);

        Button btnadd = findViewById(R.id.btnadd);
        EditText tendn = findViewById(R.id.tdn);
        EditText matkhau = findViewById(R.id.mk);
         admin= findViewById(R.id.admin);
        user= findViewById(R.id.user);





        mangTK = new ArrayList<>();
        adapter = new Account_Adapter(getApplicationContext(), R.layout.ds_taikhoan, mangTK);
//        lv.setAdapter(adapter);
        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS taikhoan(tendn VARCHAR(20) PRIMARY KEY, matkhau VARCHAR(50), quyen VARCHAR(50))");
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tendn.getText().toString();
                String password = matkhau.getText().toString();
                String quyen = "";
                if(admin.isChecked()){
                    quyen="admin";
                }
                if (user.isChecked()) {
                    quyen = "user";}


                // Thêm tài khoản vào cơ sở dữ liệu
                database.QueryData("INSERT INTO taikhoan VALUES('" + username + "', '" + password + "', '" + quyen + "')");
                Toast.makeText(AddAccount_Activity.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
                // Chuyển đến Activity thứ hai
                Intent intent = new Intent(AddAccount_Activity.this, AccountAdmin_Activity.class);
                startActivity(intent);
            }
        });

    }
}