package com.developer.wallpaper.adapter;

import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.databinding.BindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;

import com.developer.wallpaper.R;
import com.google.android.material.navigation.NavigationView;

public class DataBindingAdapter {

    @BindingAdapter("setgridview")
    public static void setGridView(GridView view,PostsAdapter adapter){
        view.setAdapter(adapter);
    }

    @BindingAdapter("setloading")
    public static void setLoading(View view,String b){
        if (b!=null){
            view.setVisibility(View.GONE);
        }

    }
}
