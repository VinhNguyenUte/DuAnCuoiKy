package com.example.addsp.Model;

import java.io.Serializable;

public class CarPostModel implements Serializable {
    private int postId;       // ID bài đăng
    private int userId;       // Người đăng bán xe
    private String brand;     // Thương hiệu xe
    private String name;      // Tên xe
    private String description; // Mô tả xe
    private String price;     // Giá xe
    private String status;    // Trạng thái duyệt (Chưa duyệt, Đã duyệt, ...)
    private String createdAt; // Ngày đăng bài

    // Constructor
    public CarPostModel(int postId, int userId, String brand, String name, String description, String price, String status, String createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getter và Setter
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
