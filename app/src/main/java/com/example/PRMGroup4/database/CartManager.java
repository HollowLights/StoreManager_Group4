package com.example.PRMGroup4.database;

import com.example.PRMGroup4.model.ProductDetail;
import com.example.PRMGroup4.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Cart> items;

    private CartManager() {
        items = new ArrayList<>();
    }

    public List<Cart> getGioHangList() {
        return items;
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public List<Cart> getItems() {
        return items;
    }

    public void addItem(ProductDetail sanPham) {
        for (Cart item : items) {
            if (item.getSanPham().getMasp().equals(sanPham.getMasp())) {
                item.setSoLuong(item.getSoLuong() + 1); // Tăng số lượng
                return; // Trả về ngay sau khi tăng số lượng
            }
        }
        // Nếu sản phẩm không tồn tại, thêm mới với số lượng 1
        items.add(new Cart(sanPham, 1));
    }
    public void removeItem(int position) {
        if (position >= 0 && position < items.size()) {

            items.remove(position);
        }

}



    public float getTongTien() {
        float tong = 0;
        for (Cart item : items) {
            tong += item.getTongGia();
        }
        return tong;
    }
    public void resetTongTien() {
        // Không cần làm gì ở đây, tổng tiền sẽ tự động trở về 0
        // khi giỏ hàng trống, nhưng có thể dùng để thông báo nếu cần.
        // Bạn có thể tạo một biến tổng tiền và quản lý nó tại đây nếu cần.
    }
    // Xóa toàn bộ giỏ hàng
    public void clearGioHang() {
        items.clear(); // Xóa danh sách giỏ hàng
        resetTongTien(); // Đặt tổng tiền về 0 nếu cần
    }



}