package com.example.planodeatividadesdasemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //1) Desenvolva uma aplicação que faça a chamadas dos métodos callbacks, ou similares no Framework Utilizado uma classe Log.

    //Chamado quando a Activity é criada pela primeira vez


    //Chamado quando a Activity fica visível ao usuário
    //Código para inicializar recursos
    protected void onStart(){
        super.onStart();
        Log.d("Ciclo de vida", "OnStart");
    }

    //Chamado quando a Activity começa a interagir com o usuário
    protected void onResume(){
        super.onResume();
        Log.d("Ciclo de vida", "OnResume");
    }

    //Chamado quando o sistema está prestes a retomar outra Activity
    protected void onPause(){
        super.onPause();
        Log.d("Ciclo de vida", "OnPause");
    }

    //Chamado quando a Activity não está mais visível ao usuário
    protected void onStop(){
        super.onStop();
        Log.d("Ciclo de vida", "OnStop");
    }

    //Chamado antes da Activity ser destruída
    protected void onDestroy(){
        super.onDestroy();
        Log.d("Ciclo de vida", "OnDestroy");
    }

    //2) Construa uma aplicação que com duas Activitys A e B, e que ao clicar em um botão abra segunda Activity
    //3) Construa uma aplicação passe uma mensagem texto de uma AtividadeA para uma AtividadeB e exiba na tela
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openActivityB(View v) {

        EditText editTextMessage = findViewById(R.id.editTextMessage);
        String message = editTextMessage.getText().toString();

        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }
}