package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Beef extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beef);
    }

    public void arrow(View view) {
        Intent intent =new Intent(Beef.this, HomeActivity.class);
        startActivity(intent);
    }
}