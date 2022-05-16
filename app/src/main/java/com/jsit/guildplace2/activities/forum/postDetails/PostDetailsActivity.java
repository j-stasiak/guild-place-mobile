package com.jsit.guildplace2.activities.forum.postDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.jsit.guildplace2.R;

public class PostDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        Intent i = this.getIntent();
        String id = i.getStringExtra("id");

        PostController controller = new PostController(new GetPostResponseListener() {
            @Override
            public void onSuccessfulResponse(Post post) {
                TextView title = findViewById(R.id.postDetailsTitle);
                TextView author = findViewById(R.id.postDetailsAuthor);
                TextView message = findViewById(R.id.postDetailsMessage);
                title.setText(post.title);
                author.setText(String.format("by %s", post.author.getEmail()));
                message.setText(post.message);
            }
        });

        controller.sendRequest(id);
    }
}