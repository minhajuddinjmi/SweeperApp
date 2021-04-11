package com.example.sweeperapp;

public class UserSignUp {
    String fullName, address, district, state, email, phoneNo, password, task;

    public UserSignUp(String fullName, String address, String district, String state, String email, String phoneNo, String password, String task) {
        this.fullName = fullName;
        this.address = address;
        this.district = district;
        this.state = state;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.task = task;
    }

    public UserSignUp(String fullName, String address, String district, String state, String email, String phoneNo, String password) {
        this.fullName = fullName;
        this.address = address;
        this.district = district;
        this.state = state;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.task = "No Task";
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
