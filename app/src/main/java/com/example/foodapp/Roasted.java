package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Roasted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roasted);
    }

    public void arrow(View view) {
        Intent intent =new Intent(Roasted.this, HomeActivity.class);
        startActivity(intent);
    }
}