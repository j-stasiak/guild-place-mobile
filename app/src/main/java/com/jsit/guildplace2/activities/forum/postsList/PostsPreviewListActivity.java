package com.jsit.guildplace2.activities.forum.postsList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.jsit.guildplace2.activities.forum.postDetails.PostDetailsActivity;
import com.jsit.guildplace2.databinding.ActivityPostsPreviewListBinding;

public class PostsPreviewListActivity extends AppCompatActivity {

    ActivityPostsPreviewListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostsPreviewListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        PostsPreviewsController controller = new PostsPreviewsController(new GetPostsResponseListener() {
            @Override
            public void onSuccessfulResponse(PostPreview[] postPreviews) {
                PostsListAdapter listAdapter = new PostsListAdapter(PostsPreviewListActivity.this, postPreviews);
                binding.postsListView.setAdapter(listAdapter);
                binding.postsListView.setClickable(true);
                binding.postsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(PostsPreviewListActivity.this, PostDetailsActivity.class);
                        intent.putExtra("id", postPreviews[i].forumPost);
                        startActivity(intent);
                    }
                });
            }
        });

        controller.sendRequest();
    }
}