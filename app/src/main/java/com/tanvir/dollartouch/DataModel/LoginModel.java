package com.tanvir.dollartouch.DataModel;

public class LoginModel {
    String message;
    String  token;

    public LoginModel(){

    }
    public LoginModel(String message, String token) {
        this.message = message;
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
