package com.hello.ourApplication.Retrofit;

import com.hello.ourApplication.DTO.Login;
import com.hello.ourApplication.DTO.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("/login")
    Call<LoginResponse> getLoginResponse(@Body Login login);
}
