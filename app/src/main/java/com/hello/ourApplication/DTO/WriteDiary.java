package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

public class WriteDiary {
    @SerializedName("operation")
    private String operation;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("content")
    private String content;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public WriteDiary(String inputId, String inputContent){
        this.operation = "writeDiary";
        this.user_id = inputId;
        this.content = inputContent;
    }
}
