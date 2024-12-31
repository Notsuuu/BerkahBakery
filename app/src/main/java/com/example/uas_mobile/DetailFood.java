package com.example.uas_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailFood extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        ImageView photo = findViewById(R.id.imgDetail);
        TextView name = findViewById(R.id.DetailName);
        TextView desc = findViewById(R.id.DetailDesc);
        Button btnResep = findViewById(R.id.btnResep);
        Button btnKembali = findViewById(R.id.btnKembali);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            photo.setImageResource(bundle.getInt("photo"));
            name.setText(bundle.getString("name"));
            desc.setText(bundle.getString("desc"));

            int foodIndex = bundle.getInt("food_index", -1); // Indeks makanan yang dipilih
            Log.d("DetailFood", "Food Index: " + foodIndex);

            btnResep.setOnClickListener(v -> {
                Intent intent = new Intent(DetailFood.this, ResepActivity.class);
                intent.putExtra("photo", bundle.getInt("photo"));
                intent.putExtra("food_index", foodIndex); // Kirim indeks ke ResepActivity
                startActivity(intent);
            });
        }

        btnKembali.setOnClickListener(v -> finish());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
