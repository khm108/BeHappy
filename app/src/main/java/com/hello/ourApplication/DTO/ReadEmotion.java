package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

public class ReadEmotion {
    @SerializedName("operation")
    private String operation;
    @SerializedName("user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ReadEmotion(String inputId) {
        this.operation = "readEmotion";
        this.userId = inputId;
    }
}
