package com.example.PRMGroup4.adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.PRMGroup4.R;
import com.example.PRMGroup4.database.Database;
import com.example.PRMGroup4.model.Account;

import java.util.List;

public class Account_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Account> accountList;
    private Database database; // Đối tượng Database

    public Account_Adapter(Context context, int layout, List<Account> accountList) {
        this.context = context;
        this.layout = layout;
        this.accountList = accountList;
        this.database = new Database(context, "banhang.db", null, 1); // Khởi tạo đối tượng Database
    }

    @Override
    public int getCount() {
        return accountList.size();
    }

    @Override
    public Object getItem(int position) {
        return accountList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewtemp;
        if (view == null) {
            viewtemp = View.inflate(viewGroup.getContext(), R.layout.ds_taikhoan, null);
        } else {
            viewtemp = view;
        }

        Account tt = accountList.get(i);
        TextView tendn = viewtemp.findViewById(R.id.tdn1);
        TextView matkhau = viewtemp.findViewById(R.id.mk1);
        TextView quyenhang = viewtemp.findViewById(R.id.quyen1);
        ImageButton sua = viewtemp.findViewById(R.id.imgsua);
        ImageButton xoa = viewtemp.findViewById(R.id.imgxoa);

        tendn.setText(tt.getTdn());
        matkhau.setText(tt.getMk());
        quyenhang.setText(tt.getQuyen());

        // Xử lý sự kiện cho ImageButton "Sửa"
        sua.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());


            View dialogView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_sua_tai_khoan, null);
            EditText editTdn = dialogView.findViewById(R.id.tdn);
            EditText editMk = dialogView.findViewById(R.id.mk);
            RadioButton user = dialogView.findViewById(R.id.user);

            RadioButton admin = dialogView.findViewById(R.id.admin);


            editTdn.setText(tt.getTdn());
            editMk.setText(tt.getMk());

            // Đặt quyền hiện tại cho RadioButton
            switch (tt.getQuyen()) {
                case "admin":
                    admin.setChecked(true);
                    break;
                case "user":
                    user.setChecked(true);
                    break;

            }

            builder.setView(dialogView)
                    .setPositiveButton("Lưu", (dialog, which) -> {
                        String newTdn = editTdn.getText().toString().trim();
                        String newMk = editMk.getText().toString().trim();
                        String quyen = user.isChecked() ? "user" : "admin";

                        SQLiteDatabase db = database.getWritableDatabase(); // Sử dụng đối tượng Database
                        ContentValues values = new ContentValues();
                        values.put("tendn", newTdn);
                        values.put("matkhau", newMk);
                        values.put("quyen", quyen);

                        // Cập nhật dữ liệu
                        int rowsAffected = db.update("taikhoan", values, "tendn = ?", new String[]{tt.getTdn()});
                        if (rowsAffected > 0) {
                            tt.setTdn(newTdn);
                            tt.setMk(newMk);
                            tt.setQuyen(quyen);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

            builder.show();
        });

        // Xử lý sự kiện cho ImageButton "Xóa"
        xoa.setOnClickListener(v -> {
            new AlertDialog.Builder(viewGroup.getContext())
                    .setTitle("Xác nhận")
                    .setMessage("Bạn có chắc chắn muốn xóa tài khoản này?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        SQLiteDatabase db = database.getWritableDatabase(); // Sử dụng đối tượng Database
                        int rowsAffected = db.delete("taikhoan", "tendn = ?", new String[]{tt.getTdn()});
                        if (rowsAffected > 0) {
                            accountList.remove(i);
                            notifyDataSetChanged();
                            Toast.makeText(viewGroup.getContext(), "Xóa tài khoản thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(viewGroup.getContext(), "Không tìm thấy tài khoản để xóa", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Không", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        return viewtemp;
    }
}
