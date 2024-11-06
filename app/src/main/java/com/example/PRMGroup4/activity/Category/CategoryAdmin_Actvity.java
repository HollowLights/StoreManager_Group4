package com.example.PRMGroup4.activity.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.PRMGroup4.activity.Order.OrderAdmin_Activity;
import com.example.PRMGroup4.activity.Product.ProductAdmin_Activity;
import com.example.PRMGroup4.activity.User.AccountAdmin_Activity;
import com.example.PRMGroup4.activity.User.PersonalPageAdmin_Activity;
import com.example.PRMGroup4.activity.ultilities.HomePageAdmin_Activity;
import com.example.PRMGroup4.activity.ultilities.Login_Activity;
import com.example.PRMGroup4.adapter.Category_Adapter;
import com.example.PRMGroup4.R;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CategoryAdmin_Actvity extends AppCompatActivity {
    private Database database;
    private ListView lv;
    private FloatingActionButton addButton;
    private ArrayList<Category> mangNSP;
    private Category_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomsanpham_admin_actvity);

        initializeViews();
        setupDatabase();
        loadData();
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
        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), AddCategory_Activity.class);
            startActivity(intent);
        });
    }

    private void initializeViews() {
        lv = findViewById(R.id.listtk);
        addButton = findViewById(R.id.btnthem);
        mangNSP = new ArrayList<>();

        adapter = new Category_Adapter(CategoryAdmin_Actvity.this, mangNSP, true);

        lv.setAdapter(adapter);
    }

    private void setupDatabase() {
        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS nhomsanpham(maso INTEGER PRIMARY KEY AUTOINCREMENT, tennsp NVARCHAR(200), anh BLOB)");
    }

    private void loadData() {
        Cursor cursor = database.GetData("SELECT * FROM nhomsanpham");
        mangNSP.clear();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String maso = cursor.getString(0);
                String tennsp = cursor.getString(1);
                byte[] blob = cursor.getBlob(2);
                mangNSP.add(new Category(maso, tennsp, blob));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "Null load dữ liuej", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }
    private byte[] convertBitmapToByteArray(int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}