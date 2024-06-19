package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private EditText editText;
    private Button buttonCari;
    private RecyclerView recyclerView;
    private ArrayList<Itemdata> values;
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = findViewById(R.id.edit_query);
        buttonCari = findViewById(R.id.button_cari);
        recyclerView = findViewById(R.id.recycler_view);
        values = new ArrayList<>();
        itemAdapter = new ItemAdapter(values, Search.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cariMakanan();
            }
        });
    }

    private void cariMakanan() {
        String queryString = editText.getText().toString();
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Tampilkan toast bahwa sedang loading
            Toast.makeText(this, "Searching food...", Toast.LENGTH_SHORT).show();
            // Mulai eksekusi FetchBook
            new FetchBook(this, values, itemAdapter, recyclerView).execute(queryString);
        } else {
            Toast.makeText(this, "Not Connected to Internet", Toast.LENGTH_SHORT).show();
        }
    }


    public void arrow(View view) {
        Intent intent =new Intent(Search.this, HomeActivity.class);
        startActivity(intent);
    }
}