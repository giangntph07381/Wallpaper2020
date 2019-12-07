package com.developer.wallpaper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.developer.wallpaper.R;
import com.developer.wallpaper.databinding.ActivityImgBinding;
import com.developer.wallpaper.mvp.model.Post;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IMG extends AppCompatActivity {

    private ActivityImgBinding activityImgBinding;
    private ImageView set, save;
    private ProgressDialog progressDialog;
    private Post post;
    private static final String[] p = {
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityImgBinding = DataBindingUtil.setContentView(this, R.layout.activity_img);
        progressDialog = new ProgressDialog(this);
        Intent intent = getIntent();
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
}
