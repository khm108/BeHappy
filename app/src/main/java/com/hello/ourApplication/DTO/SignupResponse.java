package com.hello.ourApplication.DTO;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {
    @SerializedName("statusCode")
    public String statusCode;

    @SerializedName("body")
    public String token;

    public String getStatusCode() {
        return statusCode;
    }

    public String getToken() {
        return token;
    }
}
