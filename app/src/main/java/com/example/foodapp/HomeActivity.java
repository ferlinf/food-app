package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void search(View view) {
        Intent intent =new Intent(HomeActivity.this, Search.class);
        startActivity(intent);
    }

    public void cheese(View view) {
        Intent intent =new Intent(HomeActivity.this, Cheese.class);
        startActivity(intent);
    }

    public void beef(View view) {
        Intent intent =new Intent(HomeActivity.this, Beef.class);
        startActivity(intent);
    }

    public void smooth(View view) {
        Intent intent =new Intent(HomeActivity.this, Smooties.class);
        startActivity(intent);
    }

    public void Roasted(View view) {
        Intent intent =new Intent(HomeActivity.this, Roasted.class);
        startActivity(intent);
    }
}