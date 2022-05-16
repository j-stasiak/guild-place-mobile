package com.jsit.guildplace2.activities.forum.postDetails;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jsit.guildplace2.activities.forum.ForumService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostController implements Callback<GetPostResult> {
    static final String BASE_URL = "http://10.0.2.2:3000";

    GetPostResponseListener responseListener;

    public PostController(GetPostResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void sendRequest(String id) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ForumService forumService = retrofit.create(ForumService.class);

        Call<GetPostResult> call = forumService.getPost(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<GetPostResult> call, @NonNull Response<GetPostResult> response) {
        if (response.isSuccessful() && response.body() != null) {
            responseListener.onSuccessfulResponse(response.body().data);
        }
    }

    @Override
    public void onFailure(Call<GetPostResult> call, Throwable t) {
        t.printStackTrace();
    }
}
