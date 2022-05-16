package com.jsit.guildplace2.activities.auth.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsit.guildplace2.activities.auth.AuthService;
import com.jsit.guildplace2.activities.forum.postsList.PostsPreviewListActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginController implements Callback<LoginResult> {
    // probably should be in some kind of config...
    static final String BASE_URL = "http://10.0.2.2:3000";
    public static final String PREFERENCES_FILE = "user";

    Context context;

    public LoginController(Context context) {
        this.context = context;
    }

    public void sendRequest(LoginRequest data) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AuthService authService = retrofit.create(AuthService.class);

        Call<LoginResult> call = authService.login(data);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<LoginResult> call, @NonNull Response<LoginResult> response) {
        if (response.isSuccessful()) {
            Log.d("LOGIN", "Login successful!");
            SharedPreferences.Editor editor = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE).edit();
            LoginResult result = response.body();
            editor.putString("email", result.data.email);
            editor.putString("id", result.data._id);
            editor.putString("avatar", result.data.avatar);
            editor.putString("role", result.data.role);
            editor.apply();

            Intent i = new Intent(this.context, PostsPreviewListActivity.class);
            this.context.startActivity(i);
        } else {
            Log.d("LOGIN", "Login failed!");
            try {
                Log.d("LOGIN", response.errorBody().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<LoginResult> call, Throwable t) {
        Log.d("LOGIN", "Login failed!");
        t.printStackTrace();
    }
}
