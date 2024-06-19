package com.example.foodapp;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask; // Import AsyncTask
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.example.foodapp.Itemdata;


import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

 public class FetchBook extends AsyncTask<String, Void, String> { // Extend AsyncTask

     private ArrayList<Itemdata> values;
     private ItemAdapter itemAdapter;
     private RecyclerView recyclerView;
     private Context context;

     public FetchBook(Context context, ArrayList<Itemdata> values,
                      ItemAdapter itemAdapter, RecyclerView recyclerView) {
         this.context = context;
         this.values = values;
         this.itemAdapter = itemAdapter;
         this.recyclerView = recyclerView;
     }


     @Override
     protected String doInBackground(String... strings) {
         String queryString = strings[0];
         String encodedQueryString = Uri.encode(queryString); // Encoding query string
         String mealsJSONString = "";

         try {
             URL url = new URL("https://www.themealdb.com/api/json/v1/1/search.php?s=" + encodedQueryString);
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

             InputStream inputStream = urlConnection.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             StringBuilder builder = new StringBuilder();

             String line;
             while ((line = reader.readLine()) != null) {
                 builder.append(line);
             }

             // Parse JSON response
             JSONObject response = new JSONObject(builder.toString());
             JSONArray mealsArray = response.getJSONArray("meals");
             JSONArray modifiedMealsArray = new JSONArray();

             // Iterate through meals array and extract required information
             for (int i = 0; i < mealsArray.length(); i++) {
                 JSONObject mealObject = mealsArray.getJSONObject(i);
                 JSONObject modifiedMealObject = new JSONObject();

                 // Extract required information
                 modifiedMealObject.put("strMeal", mealObject.getString("strMeal"));
                 modifiedMealObject.put("strCategory", mealObject.getString("strCategory"));
                 modifiedMealObject.put("strMealThumb", mealObject.getString("strMealThumb"));
                 modifiedMealObject.put("strInstructions", mealObject.getString("strInstructions"));
                 modifiedMealObject.put("strIngredient1", mealObject.getString("strIngredient1"));
                 modifiedMealObject.put("strIngredient2", mealObject.getString("strIngredient2"));
                 modifiedMealObject.put("strIngredient3", mealObject.getString("strIngredient3"));
                 modifiedMealObject.put("strIngredient4", mealObject.getString("strIngredient4"));
                 modifiedMealObject.put("strIngredient5", mealObject.getString("strIngredient5"));
                 modifiedMealObject.put("strIngredient6", mealObject.getString("strIngredient6"));
                 modifiedMealObject.put("strIngredient7", mealObject.getString("strIngredient7"));
                 modifiedMealObject.put("strIngredient8", mealObject.getString("strIngredient8"));
                 modifiedMealObject.put("strIngredient9", mealObject.getString("strIngredient9"));

                 // Add modified meal object to new array
                 modifiedMealsArray.put(modifiedMealObject);
             }

             // Create a new JSON object to hold modified meals array
             JSONObject modifiedResponse = new JSONObject();
             modifiedResponse.put("meals", modifiedMealsArray);

             mealsJSONString = modifiedResponse.toString();

             reader.close();
             inputStream.close();
             urlConnection.disconnect();
         } catch (IOException | JSONException e) {
             e.printStackTrace();
         }

         return mealsJSONString;
     }


     @Override
     protected void onPostExecute(String result) {
         try {
             JSONObject jsonObject = new JSONObject(result);
             JSONArray mealsArray = jsonObject.getJSONArray("meals");
             ArrayList<Itemdata> menuItems = new ArrayList<>();

             // Memeriksa apakah mealsArray tidak kosong sebelum melakukan iterasi
             if (mealsArray.length() == 0) {
                 // Jika tidak ada data yang ditemukan, tampilkan toast bahwa data tidak tersedia
                 displayToast("Data tidak ditemukan");
             } else {
                 for (int i = 0; i < mealsArray.length(); i++) {
                     JSONObject mealObject = mealsArray.getJSONObject(i);
                     String dishName = mealObject.getString("strMeal");
                     String category = mealObject.getString("strCategory");
                     String imageUrl = mealObject.getString("strMealThumb");
                     String instructions = mealObject.getString("strInstructions");
                     String ingredient1 = mealObject.getString("strIngredient1");
                     String ingredient2 = mealObject.getString("strIngredient2");
                     String ingredient3 = mealObject.getString("strIngredient3");
                     String ingredient4 = mealObject.getString("strIngredient4");
                     String ingredient5 = mealObject.getString("strIngredient5");
                     String ingredient6 = mealObject.getString("strIngredient6");
                     String ingredient7 = mealObject.getString("strIngredient7");
                     String ingredient8 = mealObject.getString("strIngredient8");
                     String ingredient9 = mealObject.getString("strIngredient9");

                     // Membuat objek Itemdata dan menambahkannya ke ArrayList menuItems
                     Itemdata menuItem = new Itemdata(dishName, category, imageUrl, instructions,
                             ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
                             ingredient6, ingredient7, ingredient8, ingredient9);
                     menuItems.add(menuItem);
                 }

                 // Setelah mendapatkan semua menuItems, Anda perlu memperbarui RecyclerView
                 // Anda dengan menggunakan adapter
                 values.clear(); // Hapus item sebelumnya dari ArrayList
                 values.addAll(menuItems); // Tambahkan semua menuItems baru
                 itemAdapter.notifyDataSetChanged(); // Perbarui RecyclerView
             }

         } catch (JSONException e) {
             e.printStackTrace();
         }
     }

     // Metode untuk menampilkan toast di UI thread
     private void displayToast(final String message) {
         Handler handler = new Handler(Looper.getMainLooper());
         handler.post(new Runnable() {
             @Override
             public void run() {
                 Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
             }
         });
     }
 }
