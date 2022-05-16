package com.jsit.guildplace2.activities.forum.postDetails;

import com.jsit.guildplace2.activities.forum.Author;

public class Post {
    String _id;
    Author author;
    String title;
    String message;
    PostReply[] replies;
    String createdAt;
    String updatedAt;
}
