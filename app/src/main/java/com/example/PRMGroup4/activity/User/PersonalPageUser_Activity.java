package com.example.PRMGroup4.activity.User;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.activity.Order.Order_Activity;
import com.example.PRMGroup4.activity.Order.Cart_Activity;
import com.example.PRMGroup4.activity.Product.SearchProduct_Activity;
import com.example.PRMGroup4.activity.ultilities.HomePageUser_Activity;
import com.example.PRMGroup4.activity.ultilities.Login_Activity;

public class PersonalPageUser_Activity extends AppCompatActivity {
    String tendn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan_nguoidung);

        Button dangxuat = findViewById(R.id.btndangxuat);
        TextView textTendn = findViewById(R.id.tendn); // TextView hiển thị tên đăng nhập

        ImageButton btntimkiem = findViewById(R.id.btntimkiem);
        ImageButton btntrangchu = findViewById(R.id.btntrangchu);
        ImageButton btncard = findViewById(R.id.btncart);
        ImageButton btndonhang = findViewById(R.id.btndonhang);
        ImageButton btncanhan = findViewById(R.id.btncanhan);

        // Lấy giá trị tendn từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
       tendn = sharedPreferences.getString("tendn", null);

        // Nếu SharedPreferences không có, lấy từ Intent
        if (tendn == null) {
            tendn = getIntent().getStringExtra("tendn");
        }

        // Kiểm tra giá trị tendn
        if (tendn != null) {
            textTendn.setText(tendn);
        } else {
            // Chưa đăng nhập, chuyển đến trang login
            Intent intent = new Intent(PersonalPageUser_Activity.this, Login_Activity.class);
            startActivity(intent);
            finish(); // Kết thúc activity nếu chưa đăng nhập
            return;
        }

        btncard.setOnClickListener(view -> {
            // Kiểm tra trạng thái đăng nhập của người dùng
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
            if (!isLoggedIn) {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), Cart_Activity.class);
                startActivity(intent);
            }
        });

        btntrangchu.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomePageUser_Activity.class);
            startActivity(intent);
        });

        btndonhang.setOnClickListener(view -> {
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
            if (isLoggedIn) {
                Intent intent = new Intent(getApplicationContext(), Order_Activity.class);
                intent.putExtra("tendn", tendn);  // Truyền tendn qua Intent
                startActivity(intent);
            } else {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            }
        });

        btncanhan.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Order_Activity.class);
            intent.putExtra("tendn", tendn);  // Truyền tendn qua Intent
            startActivity(intent);
        });

        btntimkiem.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), SearchProduct_Activity.class);
            startActivity(intent);
        });

        dangxuat.setOnClickListener(v -> {
            new AlertDialog.Builder(PersonalPageUser_Activity.this)
                    .setTitle("Đăng Xuất")
                    .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        // Xóa trạng thái đăng nhập
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("isLoggedIn", false);
                        editor.putString("tendn", null);
                        editor.apply();

                        // Quay lại Activity chính
                        Intent intent = new Intent(getApplicationContext(), HomePageUser_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish(); // Kết thúc activity
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });
    }
}
