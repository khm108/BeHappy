package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

public class WriteEmotion {
    @SerializedName("operation")
    private String operation;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("content")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WriteEmotion(String inputId, String inputContent) {
        this.operation = "writeEmotion";
        this.userId = inputId;
        this.content = inputContent;
    }
}
