package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Mendapatkan data yang dilewatkan dari Intent
        Intent intent = getIntent();
        String dishName = intent.getStringExtra("dishName");
        String category = intent.getStringExtra("category");
        String instruction = intent.getStringExtra("instruction");
        String ingredients = intent.getStringExtra("ingredients");
        String imageUrl = intent.getStringExtra("imageUrl"); // Mendapatkan URL gambar

        // Menampilkan data di TextView atau tampilan lainnya
        TextView dishNameTextView = findViewById(R.id.dishNameTextView);
        TextView categoryTextView = findViewById(R.id.categoryTextView);
        TextView instructionTextView = findViewById(R.id.instructionTextView);
        TextView ingredientsTextView = findViewById(R.id.ingredientsTextView);
        ImageView imageView = findViewById(R.id.imgdetail); // Mendapatkan ImageView

        dishNameTextView.setText(dishName);
        categoryTextView.setText(category);
        instructionTextView.setText(instruction);
        ingredientsTextView.setText(ingredients);

        // Memuat gambar menggunakan Picasso
        Picasso.get().load(imageUrl).into(imageView);
    }

    public void arrow(View view) {
        Intent intent =new Intent(DetailActivity.this, Search.class);
        startActivity(intent);
    }
}
