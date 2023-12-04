package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

public class SignIn {
    @SerializedName("operation")
    private String operation;

    @SerializedName("id")
    private String userId;

    @SerializedName("password")
    private String userPw;

    @SerializedName("name")
    private String userName;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SignIn(String inputOperation, String inputId, String inputPw, String inputName) {
        this.operation = inputOperation;
        this.userId = inputId;
        this.userPw = inputPw;
        this.userName = inputName;
    }
}
