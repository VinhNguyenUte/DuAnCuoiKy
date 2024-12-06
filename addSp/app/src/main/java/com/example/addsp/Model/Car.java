package com.example.addsp.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Car {
    private String brand;
    private String name;
    private String description;
    private double price;
    private List<Images> images= new ArrayList<>();
    private long carId;
    private List<Comment> comments = new ArrayList<>();
    private UserModel user;

    private LocalDate createdAt;

    public Car(String brand, String name, String description, double price, List<Images> images, long carId, List<Comment> comments, UserModel user, LocalDate time) {
        this.brand = brand;
        this.name = name;
        this.description = description;
        this.price = price;
        this.images = images;
        this.carId = carId;
        this.comments = comments;
        this.user = user;
        this.createdAt = time;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
