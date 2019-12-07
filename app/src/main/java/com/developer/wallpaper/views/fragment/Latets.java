package com.developer.wallpaper.views.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.developer.wallpaper.DowloadUrlAsyncTask;
import com.developer.wallpaper.Listen;
import com.developer.wallpaper.R;
import com.developer.wallpaper.activity.IMG;
import com.developer.wallpaper.adapter.PostsAdapter;
import com.developer.wallpaper.databinding.LatetsBinding;
import com.developer.wallpaper.mvp.model.Loading;
import com.developer.wallpaper.mvp.model.Post;
import com.developer.wallpaper.mvp.model.SetAdapter;

import java.util.ArrayList;
import java.util.List;


public class Latets extends FragmentData implements PostsAdapter.PostsAdapterListener , Listen {


    private LatetsBinding setgridview;
    private PostsAdapter postsAdapter;
    private static final String URL="https://pixabay.com/vi/photos/search/thien%20nhien/?cat=people&order=latest&orientation=vertical";
    private SwipeRefreshLayout swipeRefreshLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Khởi tạo view cho Fragment
        setgridview = DataBindingUtil.inflate(inflater, R.layout.latets, container, false);

        //Khởi tạo  adapter GridView
        postsAdapter = new PostsAdapter(Latets.this, getwidth());
        swipeRefreshLayout=setgridview.getRoot().findViewById(R.id.refesh);
        //Update GridView bằng Databinding
        setgridview.setAdapter(new SetAdapter(postsAdapter));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postsAdapter.clearlist();
                new DowloadUrlAsyncTask(Latets.this).execute(URL);

            }
        });

        new DowloadUrlAsyncTask(this).execute(URL);
        return setgridview.getRoot();
    }


    //Update List ảnh tùy thích Url hoặc Uri



    //Lấy chiều rộng hiện tại của Phone
    private int getwidth() {
        DisplayMetrics metrics = new DisplayMetrics();   //for all android versions
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    //Code khi click  item GridView tại đây
    @Override
    public void onPostClicked(Post post, int position) {
        Intent intent=new Intent(getContext(), IMG.class);
        intent.putExtra("post",post);
        startActivity(intent);
    }

    @Override
    public void ThanhCong(List<Post> list) {
        swipeRefreshLayout.setRefreshing(false);
        if (postsAdapter.getPostList()==null){
            Log.e("List",list.size()+"");
            setgridview.setIs(new Loading("abc"));
            postsAdapter.setPostList(list);
        }
    }

    @Override
    public void ThatBai() {
        Toast.makeText(getContext(), "Lỗi.Vui lòng thử lại", Toast.LENGTH_SHORT).show();
    }
}
