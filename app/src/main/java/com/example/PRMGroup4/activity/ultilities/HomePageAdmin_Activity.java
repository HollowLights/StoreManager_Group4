package com.example.PRMGroup4.activity.ultilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.activity.Category.CategoryAdmin_Actvity;
import com.example.PRMGroup4.activity.Order.OrderAdmin_Activity;
import com.example.PRMGroup4.activity.Product.ProductAdmin_Activity;
import com.example.PRMGroup4.activity.User.AccountAdmin_Activity;
import com.example.PRMGroup4.activity.User.PersonalPageAdmin_Activity;
import com.example.PRMGroup4.adapter.CateHomePageAdmin_Adapter;
import com.example.PRMGroup4.adapter.ProductHomePageAdmin_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Category;
import com.example.PRMGroup4.model.Product;

import java.util.ArrayList;

public class HomePageAdmin_Activity extends AppCompatActivity {

    GridView grv2;
    GridView grv1;
    ArrayList<Product> mangSPgrv1; // Danh sách cho GridView

    ArrayList<Category> mangNSPgrv2; // Danh sách cho ListView

    CateHomePageAdmin_Adapter adapterGrv2;
    ProductHomePageAdmin_Adapter adapterGrv1;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu_admin);

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
                //kiểm tra trạng thái đăng nhập của ng dùng
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

                if (!isLoggedIn) {
                    // Chưa đăng nhập, chuyển đến trang login
                    Intent intent = new Intent(getApplicationContext(),Login_Activity.class);
                    startActivity(intent);
                } else {
                    // Đã đăng nhập, chuyển đến trang 2
                    Intent intent = new Intent(getApplicationContext(), OrderAdmin_Activity.class);
                    startActivity(intent);
                }
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
        grv2=findViewById(R.id.grv2);
        grv1=findViewById(R.id.grv1);
        mangNSPgrv2= new ArrayList<>(); // Khởi tạo danh sách
        mangSPgrv1= new ArrayList<>(); // Khởi tạo danh sách
        adapterGrv2 = new CateHomePageAdmin_Adapter(this, mangNSPgrv2, false) ;

         // false để hiển thị 4 thuộc tính
        grv2.setAdapter(adapterGrv2);

        adapterGrv1= new ProductHomePageAdmin_Adapter(this, mangSPgrv1, false) ;

        grv1.setAdapter(adapterGrv1);


        database = new Database(this, "banhang.db", null, 1);

        Loaddulieubacsigridview2();
        Loaddulieubacsigridview1();
    }
    private void Loaddulieubacsigridview2() {
        Cursor dataCongViec = database.GetData("SELECT * from nhomsanpham order by random() limit 10 ");
        mangNSPgrv2.clear();

        while (dataCongViec.moveToNext()) {
            String ma = dataCongViec.getString(0);

            String ten = dataCongViec.getString(1);
            byte[] blob = dataCongViec.getBlob(2); // Lấy mảng byte từ cột chứa ảnh
            mangNSPgrv2.add(new Category(ma,ten, blob));
        }

        adapterGrv2.notifyDataSetChanged(); // Cập nhật adapter
    }

    private void Loaddulieubacsigridview1() {
        Cursor cursor = database.GetData("SELECT * FROM sanpham order by random() limit 8");
        mangSPgrv1.clear();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String masp = cursor.getString(0);
                String tensp = cursor.getString(1);
                float dongia = cursor.getFloat(2); // Giữ nguyên là float
                String mota = cursor.getString(3);
                String ghichu = cursor.getString(4);
                int soluongkho = cursor.getInt(5); // Giữ nguyên là int
                String maso = cursor.getString(6);
                byte[] blob = cursor.getBlob(7);
                mangSPgrv1.add(new Product(masp,tensp,dongia,mota,ghichu,soluongkho,maso,blob));
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "Null load dữ liệu", Toast.LENGTH_SHORT).show();
        }

        adapterGrv1.notifyDataSetChanged();
    }
}