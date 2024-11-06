package com.example.PRMGroup4.activity.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.activity.Category.CategoryAdmin_Actvity;
import com.example.PRMGroup4.activity.Order.OrderAdmin_Activity;
import com.example.PRMGroup4.activity.Product.ProductAdmin_Activity;
import com.example.PRMGroup4.activity.ultilities.HomePageAdmin_Activity;
import com.example.PRMGroup4.activity.ultilities.Login_Activity;
import com.example.PRMGroup4.adapter.Account_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AccountAdmin_Activity extends AppCompatActivity {


    Database database;
    ListView lv;
    int vitri;
    ArrayList<Account> mangTK;
    Account_Adapter adapter;
    FloatingActionButton dauconggocphai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan_admin);
        dauconggocphai = findViewById(R.id.btnthem);
        lv = findViewById(R.id.listtk);
        ImageButton btntrangchu=findViewById(R.id.btntrangchu);
        btntrangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(), HomePageAdmin_Activity.class);
                startActivity(a);
            }
        });
        ImageButton btncanhan=findViewById(R.id.btncanhan);
        btncanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kiểm tra trạng thái đăng nhập của ng dùng
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

                if (!isLoggedIn) {
                    // Chưa đăng nhập, chuyển đến trang login
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    startActivity(intent);
                } else {
                    // Đã đăng nhập, chuyển đến trang 2
                    Intent intent = new Intent(getApplicationContext(), PersonalPageAdmin_Activity.class);
                    startActivity(intent);
                }
            }
        });
        ImageButton btndonhang=findViewById(R.id.btndonhang);
        btndonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(), OrderAdmin_Activity.class);
                startActivity(a);
            }
        });
        ImageButton btnsanpham    =findViewById(R.id.btnsanpham);
        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(), ProductAdmin_Activity.class);
                startActivity(a);
            }
        });
        ImageButton btnnhomsp   =findViewById(R.id.btnnhomsp);
        btnnhomsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(), CategoryAdmin_Actvity.class);
                startActivity(a);
            }
        });
        ImageButton btntaikhoan    =findViewById(R.id.btntaikhoan);
        btntaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(), AccountAdmin_Activity.class);
                startActivity(a);
            }
        });


        dauconggocphai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(getApplicationContext(), AddAccount_Activity.class);
                startActivity(a);
            }
        });
        mangTK = new ArrayList<>();
        adapter = new Account_Adapter(getApplicationContext(), R.layout.ds_taikhoan, mangTK);
        lv.setAdapter(adapter);
        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS taikhoan(tendn VARCHAR(20) PRIMARY KEY, matkhau VARCHAR(50), quyen VARCHAR(50))");
        // Thêm 2 dòng dữ liệu
//        database.QueryData("INSERT  INTO taikhoan VALUES ('admin', '1234', 'admin')");
//        database.QueryData("INSERT  INTO taikhoan VALUES ('bac2', '1111', 'user')");
//        database.QueryData("INSERT  INTO taikhoan VALUES ('bac3', '1111', 'user')");
//        database.QueryData("INSERT  INTO taikhoan VALUES ('bac4', '1111', 'bacsi')");
        Loaddulieutaikhoan();


    }
    private void Loaddulieutaikhoan() {
        Cursor dataCongViec = database.GetData("SELECT * FROM taikhoan");
        mangTK.clear();
        while (dataCongViec.moveToNext()) {
            String tdn = dataCongViec.getString(0);
            String mk= dataCongViec.getString(1);
            String q = dataCongViec.getString(2);
            mangTK.add(new Account(tdn, mk, q));
        }
        adapter.notifyDataSetChanged();
    }
}