package com.developer.wallpaper.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.developer.wallpaper.R;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setTitle("Category");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hotboy(View view) {
        Intent intent = new Intent(Category.this, Home.class);
        intent.putExtra("URL","https://pixabay.com/vi/images/search/boy/");
        intent.putExtra("TITLE","BOY");
        startActivity(intent);
    }
    public void girl(View view) {
        Intent intent = new Intent(Category.this, Home.class);
        intent.putExtra("URL","https://pixabay.com/vi/images/search/girl/");
        intent.putExtra("TITLE","GIRL");
        startActivity(intent);
    }

    public void car(View view) {
        Intent intent = new Intent(Category.this, Home.class);
        intent.putExtra("URL","https://pixabay.com/vi/images/search/car/");
        intent.putExtra("TITLE","CAR");
        startActivity(intent);
    }

    public void animal(View view) {
        Intent intent = new Intent(Category.this, Home.class);
        intent.putExtra("URL","https://pixabay.com/vi/images/search/animal/");
        intent.putExtra("TITLE","ANIMAL");
        startActivity(intent);
    }

    public void nature(View view) {
        Intent intent = new Intent(Category.this, Home.class);
        intent.putExtra("URL","https://pixabay.com/vi/images/search/nature/");
        intent.putExtra("TITLE","NATURE");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}