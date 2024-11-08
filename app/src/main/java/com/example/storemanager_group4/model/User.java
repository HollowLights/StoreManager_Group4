package com.example.storemanager_group4.model;

public class User {
    private String userId;
    private String fullName;
    private String phone;
    private String address;
    private String email;
    private String dob;
    private String password;
    private int role;
    private byte[] image;

    // Table and column constants
    public static final String TABLE_NAME = "User";
    public static final String COLUMN_ID_USER = "userId";
    public static final String COLUMN_FULL_NAME = "fullName";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_IMAGE = "image";

    public User(){

    }

    public User(String userId, String fullName, String phone, String password, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public User(String address, String dob, String email, String fullName, byte[] image, String password, String phone, int role, String userId) {
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.fullName = fullName;
        this.image = image;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.userId = userId;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getRole() { return role; }
    public void setRole(int role) { this.role = role; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}
