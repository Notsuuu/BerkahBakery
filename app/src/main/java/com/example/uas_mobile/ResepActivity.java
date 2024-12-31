package com.example.uas_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep);

        ImageView imgResep = findViewById(R.id.imgResep);
        TextView txtBahanList = findViewById(R.id.txtBahanList);
        TextView txtResepSteps = findViewById(R.id.txtResepSteps);
        Button btnKembali = findViewById(R.id.btnKembali);  // Menambahkan tombol kembali

        // Mendapatkan indeks makanan dari Intent
        Intent intent = getIntent();
        int foodIndex = intent.getIntExtra("food_index", -1);
        Log.d("ResepActivity", "Food Index diterima: " + foodIndex);

        // Mengambil data dari strings.xml berdasarkan indeks
        String[] bahanArray = getResources().getStringArray(R.array.food_bahan);
        String[] caraMembuatArray = getResources().getStringArray(R.array.food_cara_membuat);
        int[] foodPhotos = {
                R.drawable.macaroon, R.drawable.cheesecake, R.drawable.tiramisu,
                R.drawable.creme_brulee, R.drawable.red_velvet, R.drawable.eggnog,
                R.drawable.lemonade, R.drawable.swedish_meatballs, R.drawable.carbonara
        };

        // Menampilkan data jika indeks valid
        if (foodIndex >= 0 && foodIndex < bahanArray.length && foodIndex < caraMembuatArray.length) {
            txtBahanList.setText(bahanArray[foodIndex]);
            txtResepSteps.setText(caraMembuatArray[foodIndex]);
            imgResep.setImageResource(foodPhotos[foodIndex]);
        } else {
            txtBahanList.setText("Data bahan tidak tersedia.");
            txtResepSteps.setText("Data cara membuat tidak tersedia.");
        }

        // Menambahkan aksi pada tombol kembali
        btnKembali.setOnClickListener(v -> finish());
    }
}
