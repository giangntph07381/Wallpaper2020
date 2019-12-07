package com.developer.wallpaper.mvp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

public class Post implements Serializable {
    public String imageUrl;

    public Post() {
    }

    public Post(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get().load(imageUrl).centerCrop().fit().into(view);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
