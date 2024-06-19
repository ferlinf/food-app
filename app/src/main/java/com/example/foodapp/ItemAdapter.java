package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private ArrayList<Itemdata> items;
    private Context context;

    public ItemAdapter(ArrayList<Itemdata> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Itemdata itemdata = items.get(position);

        holder.dishName.setText(itemdata.getDishName());
        holder.category.setText(itemdata.getCategory());
        Picasso.get().load(itemdata.getImageUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menghandle klik item
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("dishName", itemdata.getDishName());
                intent.putExtra("category", itemdata.getCategory());
                intent.putExtra("instruction", itemdata.getInstruction());
                intent.putExtra("ingredients", getIngredientsText(itemdata));
                intent.putExtra("imageUrl", itemdata.getImageUrl()); // Menambahkan URL gambar ke intent
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dishName, category;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dishName);
            category = itemView.findViewById(R.id.category);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    private String getIngredientsText(Itemdata itemdata) {
        StringBuilder ingredientsText = new StringBuilder();
        ingredientsText.append("Ingredients:\n");
        ingredientsText.append(itemdata.getIngredient1()).append("\n");
        ingredientsText.append(itemdata.getIngredient2()).append("\n");
        ingredientsText.append(itemdata.getIngredient3()).append("\n");
        ingredientsText.append(itemdata.getIngredient4()).append("\n");
        ingredientsText.append(itemdata.getIngredient5()).append("\n");
        ingredientsText.append(itemdata.getIngredient6()).append("\n");
        ingredientsText.append(itemdata.getIngredient7()).append("\n");
        ingredientsText.append(itemdata.getIngredient8()).append("\n");
        ingredientsText.append(itemdata.getIngredient9()).append("\n");
        return ingredientsText.toString();
    }
}
