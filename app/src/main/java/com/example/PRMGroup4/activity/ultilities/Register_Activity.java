package com.example.PRMGroup4.activity.ultilities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.adapter.Account_Adapter;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Account;

import java.util.ArrayList;

public class Register_Activity extends AppCompatActivity {

    Database database;


    ArrayList<Account> mangTK;
    Account_Adapter adapter;

    String spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki_tai_khoan);

        Button btnadd = findViewById(R.id.btnDangki);
        EditText tendn = findViewById(R.id.tdn);
        EditText matkhau = findViewById(R.id.mk);
        EditText nhaplaimatkhau = findViewById(R.id.nhaplaimk);
        Spinner spinner = findViewById(R.id.quyen);
        TextView ql=findViewById(R.id.ql);
        ql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(Register_Activity.this, Login_Activity.class);
                startActivity(a);
            }
        });
        ArrayList<String> ar = new ArrayList<>();
        ar.add("user");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, ar);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spn = ar.get(i);
            }


            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        mangTK = new ArrayList<>();
        adapter = new Account_Adapter(getApplicationContext(), R.layout.ds_taikhoan, mangTK);
//        lv.setAdapter(adapter);
        database = new Database(this, "banhang.db", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS taikhoan(tendn VARCHAR(20) PRIMARY KEY, matkhau VARCHAR(50), quyen VARCHAR(50))");



        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = tendn.getText().toString().trim();
                String password = matkhau.getText().toString().trim();
                String nhaplaimk = nhaplaimatkhau.getText().toString().trim();

                // Kiểm tra xem tên đăng nhập và mật khẩu có rỗng không
                if (username.isEmpty() || password.isEmpty() || nhaplaimk.isEmpty()) {
                    Toast.makeText(Register_Activity.this, "Tên đăng nhập và mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra xem mật khẩu và mật khẩu xác nhận có trùng nhau không
                if (!password.equals(nhaplaimk)) {
                    Toast.makeText(Register_Activity.this, "Mật khẩu không khớp, vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra xem username có tồn tại trong cơ sở dữ liệu không
                Cursor cursor = database.GetData("SELECT * FROM taikhoan WHERE tendn = '" + username + "'");
                if (cursor.getCount() > 0) {
                    Toast.makeText(Register_Activity.this, "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thêm tài khoản vào cơ sở dữ liệu
                database.QueryData("INSERT INTO taikhoan VALUES('" + username + "', '" + password + "', '" + spn + "')");
                Toast.makeText(Register_Activity.this, "Đăng kí tài khoản thành công", Toast.LENGTH_LONG).show();
                // Chuyển đến Activity thứ hai
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            }
        });
    }
}