package com.hello.ourApplication.Retrofit;

import com.hello.ourApplication.DTO.Login;
import com.hello.ourApplication.DTO.LoginResponse;
import com.hello.ourApplication.DTO.Signup;
import com.hello.ourApplication.DTO.SignupResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("behappy/login")
    Call<LoginResponse> getLoginResponse(@Body Login login);

    @POST("behappy/signup")
    Call<SignupResponse> getSignupResponse(@Body Signup signup);
}
