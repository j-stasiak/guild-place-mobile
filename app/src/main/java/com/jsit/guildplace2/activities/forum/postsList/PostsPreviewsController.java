package com.jsit.guildplace2.activities.forum.postsList;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsit.guildplace2.activities.forum.ForumService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsPreviewsController implements Callback<GetPostsPreviewsResult> {
    // probably should be in some kind of config...
    static final String BASE_URL = "http://10.0.2.2:3000";

    GetPostsResponseListener responseListener;

    public PostsPreviewsController(GetPostsResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void sendRequest() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ForumService forumService = retrofit.create(ForumService.class);

        Call<GetPostsPreviewsResult> call = forumService.getPosts();
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<GetPostsPreviewsResult> call, @NonNull Response<GetPostsPreviewsResult> response) {
        if (response.isSuccessful() && response.body() != null) {
            responseListener.onSuccessfulResponse(response.body().data);
        }
    }

    @Override
    public void onFailure(Call<GetPostsPreviewsResult> call, Throwable t) {
        t.printStackTrace();
    }
}
