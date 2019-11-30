package com.developer.wallpaper.views.fragment;

import android.os.Bundle;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.developer.wallpaper.R;
import com.developer.wallpaper.adapter.PostsAdapter;
import com.developer.wallpaper.databinding.LatetsBinding;
import com.developer.wallpaper.mvp.model.Loading;
import com.developer.wallpaper.mvp.model.Post;
import com.developer.wallpaper.mvp.model.SetAdapter;

import java.util.ArrayList;
import java.util.List;


public class Latets extends FragmentData implements PostsAdapter.PostsAdapterListener {


    private LatetsBinding setgridview;
    private PostsAdapter postsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Khởi tạo view cho Fragment
        setgridview = DataBindingUtil.inflate(inflater, R.layout.latets, container, false);

        //Khởi tạo  adapter GridView
        postsAdapter = new PostsAdapter(getPosts(), Latets.this, getwidth());

        //Update GridView bằng Databinding
        setgridview.setAdapter(new SetAdapter(postsAdapter));

        //Hide Loading
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setgridview.setIs(new Loading("true"));
            }
        }, 2000);

        return setgridview.getRoot();
    }


    //Update List ảnh tùy thích Url hoặc Uri
    private List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/" + i + ".jpg");
            posts.add(post);
        }

        return posts;
    }


    //Lấy chiều rộng hiện tại của Phone
    private int getwidth() {
        DisplayMetrics metrics = new DisplayMetrics();   //for all android versions
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    //Code khi click  item GridView tại đây
    @Override
    public void onPostClicked(Post post, int position) {
        Toast.makeText(getContext(), "Vị trí ảnh số:" + (position + 1), Toast.LENGTH_SHORT).show();
    }
}
