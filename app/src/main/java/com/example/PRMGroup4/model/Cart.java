package com.example.PRMGroup4.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Cart implements Parcelable {
    private ProductDetail sanPham;
    private int soLuong;

    public Cart(ProductDetail sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }
    protected Cart(Parcel in) {
        sanPham = in.readParcelable(ProductDetail.class.getClassLoader());
        soLuong = in.readInt();
    }
    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(sanPham, flags);
        dest.writeInt(soLuong);
    }

    // Getter và Setter
    public ProductDetail getSanPham() {
        return sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    // Phương thức tính tổng giá
    public float getTongGia() {
        return sanPham.getDongia() * soLuong; // Giả sử ChiTietSanPham có phương thức getDongia
    }

}