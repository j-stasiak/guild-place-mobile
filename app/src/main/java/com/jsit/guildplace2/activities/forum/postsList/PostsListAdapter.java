package com.jsit.guildplace2.activities.forum.postsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jsit.guildplace2.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostsListAdapter extends ArrayAdapter<PostPreview> {
    public PostsListAdapter(Context context, PostPreview[] postPreviews) {
        super(context, R.layout.post_preview, R.id.ghostTextView, postPreviews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PostPreview postPreview = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_preview, parent, false);
        }

        TextView title = convertView.findViewById(R.id.postTitle);
        TextView author = convertView.findViewById(R.id.postAuthor);
        TextView creationTime = convertView.findViewById(R.id.postCreationTime);

        title.setText(postPreview.title);
        author.setText(postPreview.author.getEmail());
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        String formattedDate = postPreview.createdAt;
        try {
            formattedDate = outputFormat.format(inputFormat.parse(postPreview.createdAt));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        creationTime.setText(formattedDate);

        return super.getView(position, convertView, parent);
    }
}
