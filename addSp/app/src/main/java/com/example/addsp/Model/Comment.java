package com.example.addsp.Model;

import android.os.Parcel;
import android.os.Parcelable;



import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Comment  {
    private Long commentId;

    private UserModel user;

    private Car car;
    private String content;
    private double rating=0;
    private Long likes = 0L;
    private Long dislike = 0L;


    private Set<Long> likedByUsers = new HashSet<>();


    private Set<Long> dislikedByUsers = new HashSet<>();

    private LocalDate createdAt;

    public Comment(Long commentId, UserModel user, Car car, String content, double rating, Long likes, Long dislike, Set<Long> likedByUsers, Set<Long> dislikedByUsers, LocalDate createdAt) {
        this.commentId = commentId;
        this.user = user;
        this.car = car;
        this.content = content;
        this.rating = rating;
        this.likes = likes;
        this.dislike = dislike;
        this.likedByUsers = likedByUsers;
        this.dislikedByUsers = dislikedByUsers;
        this.createdAt = createdAt;
    }

    public Long getCommentId() {
        return commentId;
    }

    public UserModel getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public String getContent() {
        return content;
    }

    public double getRating() {
        return rating;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislike() {
        return dislike;
    }

    public Set<Long> getLikedByUsers() {
        return likedByUsers;
    }

    public Set<Long> getDislikedByUsers() {
        return dislikedByUsers;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public void setDislike(Long dislike) {
        this.dislike = dislike;
    }

    public void setLikedByUsers(Set<Long> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public void setDislikedByUsers(Set<Long> dislikedByUsers) {
        this.dislikedByUsers = dislikedByUsers;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }


}
