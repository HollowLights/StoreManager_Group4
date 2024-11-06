package com.example.PRMGroup4.activity.Category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.activity.Product.ProductDetail_Activity;
import com.example.PRMGroup4.adapter.ProductCategory_Adapter;
import com.example.PRMGroup4.database.DatabaseHelper;
import com.example.PRMGroup4.model.Product;

import java.util.ArrayList; // Import ArrayList
import java.util.List;

public class Category_Activity extends AppCompatActivity {
    private GridView grv;
    private ArrayList<Product> productList; // Change to ArrayList
    private ProductCategory_Adapter productAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc_san_pham);
        ImageButton btntimkiem = findViewById(R.id.btntimkiem);
        ImageButton btntrangchu = findViewById(R.id.btntrangchu);
        ImageButton btncard = findViewById(R.id.btncart);
        ImageButton btndonhang = findViewById(R.id.btndonhang);
        ImageButton btncanhan = findViewById(R.id.btncanhan);
        // Initialize the GridView and DatabaseHelper
        grv = findViewById(R.id.grv);
        dbHelper = new DatabaseHelper(this);

        // Retrieve nhomSpId from the Intent
        String nhomSpId = getIntent().getStringExtra("nhomSpId");

        // Check if nhomSpId is not null
        if (nhomSpId != null) {
            // Get the list of products by nhomSpId
            List<Product> tempProductList = dbHelper.getProductsByNhomSpId(nhomSpId); // Use a temporary variable
            if (tempProductList != null && !tempProductList.isEmpty()) {
                // Convert List to ArrayList
                productList = new ArrayList<>(tempProductList);
                // Initialize and set the adapter with the product list
                productAdapter = new ProductCategory_Adapter(this, productList, false);
                grv.setAdapter(productAdapter);
            } else {
                Toast.makeText(this, "Không tìm thấy sản phẩm nào trong nhóm này!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "ID nhóm sản phẩm không hợp lệ!", Toast.LENGTH_SHORT).show();
        }


        grv.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy sản phẩm tại vị trí được click
            Product product = productList.get(position);

            // Tạo Intent để chuyển sang ChiTietSanPham_Activity
            Intent intent = new Intent(Category_Activity.this, ProductDetail_Activity.class);

            // Truyền dữ liệu sản phẩm qua Intent
            intent.putExtra("masp", product.getMasp());
            intent.putExtra("tensp", product.getTensp());
            intent.putExtra("dongia", product.getDongia());
            intent.putExtra("mota", product.getMota());
            intent.putExtra("ghichu", product.getGhichu());
            intent.putExtra("soluongkho", product.getSoluongkho());
            intent.putExtra("maso", product.getMansp());
            intent.putExtra("anh", product.getAnh());

            // Chuyển đến trang ChiTietSanPham_Activity
            startActivity(intent);
        });

    }
}
