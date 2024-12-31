package com.example.uas_mobile;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListFood extends AppCompatActivity {
    private String[] food_name;
    private String[] food_desc;
    private TypedArray food_photo;
    private FoodAdapter adapter;
    private ArrayList<Food> foodlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_food);
        adapter = new FoodAdapter(this);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        prepare();
        addItem();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Food list = foodlist.get(i);
                Intent intent = new Intent(ListFood.this, DetailFood.class);
                intent.putExtra("name", list.getName());
                intent.putExtra("desc", list.getDesc());
                intent.putExtra("photo", list.getPhoto());
                intent.putExtra("food_index", i);
                startActivity(intent);
            }
        });

    }
    private void prepare() {
        food_name =
                getResources().getStringArray(R.array.food_name);
        food_desc =
                getResources().getStringArray(R.array.food_desc);
        food_photo =
                getResources().obtainTypedArray(R.array.food_photo);
    }
    private void addItem() {
        foodlist = new ArrayList<>();
        for (int i = 0; i < food_name.length; i++) {
            Food food = new Food();
            food.setPhoto(food_photo.getResourceId(i, -1));
            food.setName(food_name[i]);
            food.setDesc(food_desc[i]);
            foodlist.add(food);
        }
        adapter.setFood(foodlist);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Search Food..");
        return true;
    }
}
