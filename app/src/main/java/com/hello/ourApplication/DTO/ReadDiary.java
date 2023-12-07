package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReadDiary {
    @SerializedName("operation")
    private String operation;

    @SerializedName("user_id")
    private String user_id;


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

    public ReadDiary(String inputId){
        this.operation = "readDiary";
        this.user_id = inputId;
    }
}
