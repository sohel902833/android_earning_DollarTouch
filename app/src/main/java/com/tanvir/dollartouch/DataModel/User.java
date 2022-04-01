package com.tanvir.dollartouch.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class User {

   String userId,name,password,deviceId,phone,isRefer,joiningDate;
   double balance;
   int myReferCode,referCode;
   List<CategoryLastPlayModel> lastPlay=new ArrayList<>();

   public User(String userId, String name, String password, String deviceId, String phone, String isRefer, String joiningDate, double balance, int myReferCode, int referCode, List<CategoryLastPlayModel> lastPlay) {
      this.userId = userId;
      this.name = name;
      this.password = password;
      this.deviceId = deviceId;
      this.phone = phone;
      this.isRefer = isRefer;
      this.joiningDate = joiningDate;
      this.balance = balance;
      this.myReferCode = myReferCode;
      this.referCode = referCode;
      this.lastPlay = lastPlay;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getDeviceId() {
      return deviceId;
   }

   public void setDeviceId(String deviceId) {
      this.deviceId = deviceId;
   }

   public String getPhone() {
      return phone;
   }

   public void setPhone(String phone) {
      this.phone = phone;
   }

   public String getIsRefer() {
      return isRefer;
   }

   public void setIsRefer(String isRefer) {
      this.isRefer = isRefer;
   }

   public String getJoiningDate() {
      return joiningDate;
   }

   public void setJoiningDate(String joiningDate) {
      this.joiningDate = joiningDate;
   }

   public double getBalance() {
      return balance;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

   public int getMyReferCode() {
      return myReferCode;
   }

   public void setMyReferCode(int myReferCode) {
      this.myReferCode = myReferCode;
   }

   public int getReferCode() {
      return referCode;
   }

   public void setReferCode(int referCode) {
      this.referCode = referCode;
   }

   public List<CategoryLastPlayModel> getLastPlay() {
      return lastPlay;
   }

   public void setLastPlay(List<CategoryLastPlayModel> lastPlay) {
      this.lastPlay = lastPlay;
   }
}