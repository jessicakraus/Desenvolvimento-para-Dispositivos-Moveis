package com.example.planodeatividadesdasemana03;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

//3. Crie uma aplicação Activity Hello Wold,utilizando internacionalização disponível no sistema operacional, para
//praticar o seu uso, crie uma Activity conforme imagem abaixo, que exiba o texto no idioma configurado no
//dispositivo.
public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internacionalizacao);

        ImageView imageViewBrazil = findViewById(R.id.imageView);
        ImageView imageViewUSA = findViewById(R.id.imageView2);

        Locale currentLocale = getResources().getConfiguration().locale;

        if (currentLocale.equals(Locale.ENGLISH)) {
            imageViewBrazil.setImageResource(R.drawable.brazil_gray);
            imageViewUSA.setImageResource(R.drawable.usa_color);
        } else {
            imageViewBrazil.setImageResource(R.drawable.brazil_color);
            imageViewUSA.setImageResource(R.drawable.usa_gray);
        }
    }
}