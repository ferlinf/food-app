package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Smooties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smooties);
    }

    public void arrow(View view) {
        Intent intent =new Intent(Smooties.this, HomeActivity.class);
        startActivity(intent);
    }
}