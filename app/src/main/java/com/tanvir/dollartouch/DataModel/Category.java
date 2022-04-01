package com.tanvir.dollartouch.DataModel;

public class Category {
    int categoryId;
    String title,description;
    double amount,delayTime;
    int image;

    public Category(int categoryId, String title, String description, double amount, double delayTime,int image) {
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.delayTime = delayTime;
        this.image=image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(double delayTime) {
        this.delayTime = delayTime;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
