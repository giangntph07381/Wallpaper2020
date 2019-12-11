package com.developer.wallpaper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.developer.wallpaper.activity.Home;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

//
//    public void login(View view) {
//        Intent intent = new Intent(LoginActivity.this, Home.class);
//        startActivity(intent);
//
//    }
//    public void dangky(View view) {
//        Intent intent = new Intent(LoginActivity.this,CreateAccount.class);
//        startActivity(intent);
//        finish();

}
