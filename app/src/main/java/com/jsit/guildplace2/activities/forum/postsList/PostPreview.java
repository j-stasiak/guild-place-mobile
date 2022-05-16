package com.jsit.guildplace2.activities.forum.postsList;

import com.jsit.guildplace2.activities.forum.Author;

public class PostPreview {
    String _id;
    Author author;
    String createdAt;
    String title;
    String updatedAt;
    String forumPost;

    PostPreview(String _id, Author author, String createdAt, String title, String updatedAt, String forumPost) {
        this._id = _id;
        this.author = author;
        this.createdAt = createdAt;
        this.title = title;
        this.updatedAt = updatedAt;
        this.forumPost = forumPost;
    }
}
