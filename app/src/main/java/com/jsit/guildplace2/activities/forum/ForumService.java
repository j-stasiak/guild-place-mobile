package com.jsit.guildplace2.activities.forum;

import com.jsit.guildplace2.activities.forum.postDetails.GetPostResult;
import com.jsit.guildplace2.activities.forum.postsList.GetPostsPreviewsResult;
import com.jsit.guildplace2.activities.forum.postsList.PostPreview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ForumService {
    @GET("/forum")
    Call<GetPostsPreviewsResult> getPosts();

    @GET("/forum/{id}")
    Call<GetPostResult> getPost(@Path("id") String id);
}
