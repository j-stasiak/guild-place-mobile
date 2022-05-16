package com.jsit.guildplace2.activities.auth.register;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsit.guildplace2.activities.auth.AuthService;
import com.jsit.guildplace2.activities.auth.login.LoginActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterController implements Callback<Void> {
    // probably should be in some kind of config...
    static final String BASE_URL = "http://10.0.2.2:3000";

    Context context;

    public RegisterController(Context context) {
        this.context = context;
    }

    public void sendRequest(RegisterRequest data) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AuthService authService = retrofit.create(AuthService.class);

        Call<Void> call = authService.register(data);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
        if (response.isSuccessful()) {
            Toast.makeText(context, "Success! You can login now!", Toast.LENGTH_SHORT).show();
            Intent loginIntent = new Intent(context, LoginActivity.class);
            context.startActivity(loginIntent);
        } else {
            Log.d("SIGNUP", "Sign up failed!");
            if(response.errorBody() != null) {
                try {
                    Log.d("SIGNUP", response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<Void> call, Throwable t) {
        Log.d("SIGNUP", "Sign up failed!");
        t.printStackTrace();
    }
}
