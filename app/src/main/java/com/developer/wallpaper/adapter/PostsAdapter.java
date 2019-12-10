package com.developer.wallpaper.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.wallpaper.R;
import com.developer.wallpaper.databinding.ItemGirdviewBinding;
import com.developer.wallpaper.mvp.model.Post;

import java.util.List;

public class PostsAdapter extends BaseAdapter {


    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;
    private int wid;

    public  PostsAdapter(PostsAdapterListener listener, int wid) {
        this.listener = listener;
        this.wid = wid;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    public void clearlist(){
        postList.clear();
        postList=null;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (postList==null){
            return 0;
        }else
            Log.e("adaptersize",postList.size()+"");
        return
        postList.size();
    }

    @Override
    public Object getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemGirdviewBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_girdview, parent, false);

        RelativeLayout relativeLayout=binding.getRoot().findViewById(R.id.layout);
        ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
        layoutParams.width = wid/2-12;
        layoutParams.height = wid/2-12;
        binding.setPost(postList.get(position));
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    //click item recyclerview dùng interface
                    listener.onPostClicked(postList.get(position),position);
                }
            }
        });
        return binding.getRoot();
    }


   public interface PostsAdapterListener {
        void onPostClicked(Post post,int position);
    }
}
