package com.tanvir.dollartouch.DataModel;

public class CategoryLastPlayModel {
    int id,userId,categoryId;
    long lastPlay;

    public CategoryLastPlayModel(int id, int userId, int categoryId, long lastPlay) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.lastPlay = lastPlay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getLastPlay() {
        return lastPlay;
    }

    public void setLastPlay(long lastPlay) {
        this.lastPlay = lastPlay;
    }
}
