package com.tanvir.dollartouch.DataModel;

public class WithdrawModel {
    int id,userId;
    double amount;
    String state,withdrawWith,accountNo,time;

    public WithdrawModel(int id, int userId, double amount, String state, String withdrawWith, String accountNo, String time) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.state = state;
        this.withdrawWith = withdrawWith;
        this.accountNo = accountNo;
        this.time = time;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getWithdrawWith() {
        return withdrawWith;
    }

    public void setWithdrawWith(String withdrawWith) {
        this.withdrawWith = withdrawWith;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
