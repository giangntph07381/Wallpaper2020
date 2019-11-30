package com.developer.wallpaper.mvp.model;

import com.developer.wallpaper.adapter.PostsAdapter;

public class SetAdapter {
    private PostsAdapter postsAdapter;

    public SetAdapter() {
    }

    public SetAdapter(PostsAdapter postsAdapter) {
        this.postsAdapter = postsAdapter;
    }

    public PostsAdapter getPostsAdapter() {
        return postsAdapter;
    }

    public void setPostsAdapter(PostsAdapter postsAdapter) {
        this.postsAdapter = postsAdapter;
    }
}
