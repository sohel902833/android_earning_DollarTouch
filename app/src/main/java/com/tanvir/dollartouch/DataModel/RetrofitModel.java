package com.tanvir.dollartouch.DataModel;

public class RetrofitModel {
    String message;
    public RetrofitModel() {
    }

    public RetrofitModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
