package com.example.planodeatividadesdasemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView textViewMessage = findViewById(R.id.textViewMessage);
        String message = getIntent().getStringExtra("message");
        textViewMessage.setText(message);
    }
}