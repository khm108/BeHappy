package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("operation")
    private String operation;
    @SerializedName("id")
    private String userId;

    @SerializedName("password")
    private String userPw;

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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Login(String inputOperation, String inputId, String inputPw) {
        this.operation = inputOperation;
        this.userId = inputId;
        this.userPw = inputPw;
    }
}
