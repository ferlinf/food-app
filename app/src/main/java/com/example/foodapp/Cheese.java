package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cheese extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese);
    }

    public void arrow(View view) {
        Intent intent =new Intent(Cheese.this, HomeActivity.class);
        startActivity(intent);
    }
}