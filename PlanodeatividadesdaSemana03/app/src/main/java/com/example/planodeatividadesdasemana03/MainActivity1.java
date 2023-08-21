package com.example.planodeatividadesdasemana03;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

//1. Recrie o layout abaixo utilizando Linear e ConstraintLayout
public class MainActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_constraintlayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linearlayout);
    }
}