package com.example.uas_mobile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Food> foods;

    public void setFood(ArrayList<Food> food) {
        this.foods = food;
    }
    public FoodAdapter(Context context){
        this.context = context;
        foods = new ArrayList<>();
    }

    @Override
    public int getCount() {return foods.size();}

    @Override
    public Object getItem(int i) {return foods.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_food,
                    viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        Food food = (Food) getItem(i);
        viewHolder.bind(food);
        return view;
    }
    private class ViewHolder{
        private TextView txtName;
        private TextView txtPrice;
        private TextView txtDesc;
        private ImageView imgPhoto;

        ViewHolder(View view){
            txtName = view.findViewById(R.id.txt_name);
            txtDesc = view.findViewById(R.id.txt_desc);
            imgPhoto = view.findViewById(R.id.img_photo);
        }
        void bind(Food food){
            txtName.setText(food.getName());
            txtDesc.setText(food.getDesc());
            imgPhoto.setImageResource (food.getPhoto());
        }
    }
}
