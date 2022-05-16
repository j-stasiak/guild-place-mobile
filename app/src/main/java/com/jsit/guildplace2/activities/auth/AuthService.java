package com.jsit.guildplace2.activities.auth;

import com.jsit.guildplace2.activities.auth.login.LoginRequest;
import com.jsit.guildplace2.activities.auth.login.LoginResult;
import com.jsit.guildplace2.activities.auth.register.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/login")
    Call<LoginResult> login(@Body LoginRequest body);

    @POST("/signup")
    Call<Void> register(@Body RegisterRequest body);

    @POST("/logout")
    Call<Void> logout();
}
