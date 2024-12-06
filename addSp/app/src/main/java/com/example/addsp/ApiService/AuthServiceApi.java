package com.example.addsp.ApiService;


import com.example.addsp.ApiRequest.LoginRequest;
import com.example.addsp.ApiRequest.RegisterRequest;
import com.example.addsp.ApiRequest.ResetPasswordRequest;
import com.example.addsp.ApiResponse.LoginResponse;
import com.example.addsp.ApiResponse.ResetPasswordResponse;
import com.example.addsp.Model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthServiceApi {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    AuthServiceApi authServiceApi = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8000/auth/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthServiceApi.class);

    @POST("signin")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @POST("create/user")
    Call<UserModel> register(@Body UserModel userModel, @Header("Authorization") String token);
    @POST("forgot-password/send-otp")
    Call<ResetPasswordResponse> resetPassword(@Body ResetPasswordRequest resetPassword);


}
