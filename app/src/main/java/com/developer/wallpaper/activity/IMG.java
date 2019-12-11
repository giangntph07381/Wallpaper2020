package com.developer.wallpaper.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.developer.wallpaper.R;
import com.developer.wallpaper.databinding.ActivityImgBinding;
import com.developer.wallpaper.mvp.model.Post;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class IMG extends AppCompatActivity {
    private FloatingActionMenu menuGreen;
    private Handler mUiHandler = new Handler();
    private boolean is = true;
    private ImageView imageView;
    private Bitmap result;
    private String url;
    private SharedPreferences love;
    private SharedPreferences.Editor editor;
    private List<String> list;
    private LinearLayout view_layout;

    private ActivityImgBinding activityImgBinding;
    private ImageView set, save;
    private ProgressDialog progressDialog;
    private Post post;
    private static final String[] p = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        menuGreen.hideMenuButton(false);
//
//        String httpParamJSONList = love.getString("list", "");
//
//
//        final FloatingActionButton programFab4 = new FloatingActionButton(getActivity());
//        programFab4.setButtonSize(FloatingActionButton.SIZE_MINI);
//        programFab4.setColorNormal(getResources().getColor(R.color.grey));
//        programFab4.setColorPressed(getResources().getColor(R.color.colorPrimary));
//
//        if (!httpParamJSONList.isEmpty()) {
//            List<String> httpParamList =
//                    new Gson().fromJson(httpParamJSONList, new TypeToken<List<String>>() {
//                    }.getType());
//            if (!httpParamList.isEmpty()) {
//
//                for (String i : httpParamList) {
//                    if (i.equals(url)) {
//                        is = false;
//                        break;
//                    }
//                }
//                if (!is) {
//                    programFab4.setImageResource(R.drawable.my_fa_red);
//                    list.add(url);
//                } else {
//                    programFab4.setImageResource(R.drawable.my_fa_white);
//                    list.clear();
//                }
//
//            } else {
//                programFab4.setImageResource(R.drawable.my_fa_white);
//                is = true;
//            }
//        } else {
//            programFab4.setImageResource(R.drawable.my_fa_white);
//            is = true;
//        }
//        menuGreen.addMenuButton(programFab4);
//        programFab4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (is) {
//                    list.clear();
//                    list.add(url);
//                    Log.e("SIZE", list.size() + "");
//                    programFab4.setImageResource(R.drawable.my_fa_red);
//                    is = false;
//                } else {
//                    String httpParamJSONList = love.getString("list", "");
//                    List<String> httpParamList =
//                            new Gson().fromJson(httpParamJSONList, new TypeToken<List<String>>() {
//                            }.getType());
//                    boolean ia = false;
//                    for (int i = 0; i < httpParamList.size(); i++) {
//                        if (httpParamList.get(i).equals(url)) {
//                            httpParamList.remove(i);
//                            ia = true;
//                            break;
//                        }
//
//                    }
//                    if (ia) {
//                        editor = love.edit();
//                        String listabc = new Gson().toJson(httpParamList);
//                        editor.putString("list", listabc);
//                        editor.apply();
//                    }
//
//                    programFab4.setImageResource(R.drawable.my_fa_white);
//                    list.remove(0);
//                    is = true;
//                }
//            }
//        });
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityImgBinding = DataBindingUtil.setContentView(this, R.layout.activity_img);
        progressDialog = new ProgressDialog(this);
        Intent intent = getIntent();
        view_layout=activityImgBinding.getRoot().findViewById(R.id.view_layout);
        view_layout.setAnimation(AnimationUtils.loadAnimation(IMG.this,R.anim.fade));
        post = (Post) intent.getSerializableExtra("post");
        set = activityImgBinding.getRoot().findViewById(R.id.set);
        save = activityImgBinding.getRoot().findViewById(R.id.save);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Đang cài hình nền");
                progressDialog.show();
                Picasso.get().load(post.getImageUrl()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                        // loaded bitmap is here (bitmap)
                        final WallpaperManager myWallpaperManager
                                = WallpaperManager.getInstance(getApplicationContext());
                        try {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        myWallpaperManager.setBitmap(bitmap);
                                        Toast.makeText(IMG.this, "Cài thành công", Toast.LENGTH_SHORT).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    progressDialog.cancel();
                                }
                            }, 2000);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                    }
                });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hasP()){
                    progressDialog.setMessage("Đang lưu");
                    progressDialog.show();
                    saveimg(post);
                }else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(p,1000);
                    }
                }

            }
        });
        save = activityImgBinding.getRoot().findViewById(R.id.save);
        activityImgBinding.setPost(post);

    }

    public void thoat(View view) {
        finish();
    }

    private void saveimg(Post post) {
        Picasso.get().load(post.getImageUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                try {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                File file = new File(getStringpath(), "IMG" + new SimpleDateFormat("HH_mm_ss").format(new Date()) + ".jpg");
                                OutputStream fOut = new FileOutputStream(file);
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                                fOut.flush(); // Not really required
                                fOut.close();
                                Toast.makeText(IMG.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Log.e("err", e.toString());
                                e.printStackTrace();
                            }
                            progressDialog.cancel();
                        }
                    }, 2000);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    private String getStringpath() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/IMG";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return file.getPath();
    }

    private boolean hasP() {
        for (String ps : p) {
            if (ContextCompat.checkSelfPermission(this, ps) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==1000){
            progressDialog.setMessage("Đang lưu");
            progressDialog.show();
            saveimg(post);
        }
    }

    private void doShareLink(String text, String link) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        Intent chooserIntent = Intent.createChooser(shareIntent, getString(R.string.chiasebanbe));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shareIntent.putExtra(Intent.EXTRA_TEXT, text + " " + link);
            Bundle facebookBundle = new Bundle();
            facebookBundle.putString(Intent.EXTRA_TEXT, link);
            Bundle replacement = new Bundle();
            replacement.putBundle("com.facebook.katana", facebookBundle);
            chooserIntent.putExtra(Intent.EXTRA_REPLACEMENT_EXTRAS, replacement);
        } else {
            shareIntent.putExtra(Intent.EXTRA_TEXT, link);
        }
        chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(chooserIntent);
    }
    public void share(View view) {
        doShareLink("Chia se",post.getImageUrl());
    }
}
