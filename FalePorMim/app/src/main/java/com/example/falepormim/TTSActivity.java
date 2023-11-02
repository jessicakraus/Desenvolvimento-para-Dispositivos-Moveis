package com.example.falepormim;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class TTSActivity extends Activity implements OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private EditText editText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> speechList;
    private FalasDAO falasDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSpeak = findViewById(R.id.btnSpeak);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);

        // Inicialize o mecanismo TTS
        tts = new TextToSpeech(this, this);

        // Verifique se há um mecanismo TTS configurado no dispositivo
        if (isTTSAvailable()) {
            speechList = new ArrayList<>();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, speechList);
            listView.setAdapter(adapter);

            falasDAO = new FalasDAO(this);

            btnSpeak.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    speakOut();
                }
            });

            btnSpeak = findViewById(R.id.btnSpeak);
            editText = findViewById(R.id.editText);
            listView = findViewById(R.id.listView);
            btnViewSpeechList = findViewById(R.id.btnViewSpeechList);

            btnViewSpeechList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Inicie a nova atividade para mostrar a lista de falas
                    Intent intent = new Intent(TTSActivity.this, ListagemFalas.class);
                    startActivity(intent);
                }
            });
        } else {
            // Informe o usuário que não há mecanismo TTS configurado
            showMessage("Nenhum mecanismo TTS configurado no dispositivo. Por favor, configure um mecanismo TTS.");
        }
    }

    // Verifica se há um mecanismo TTS configurado
    private boolean isTTSAvailable() {
        return tts != null;
    }

    // Exibe uma mensagem para o usuário
    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Código a ser executado quando o botão "OK" for clicado
                        dialog.dismiss(); // Fecha o AlertDialog
                    }
                });
        // Criar o AlertDialog
        AlertDialog dialog = builder.create();
        // Mostrar o AlertDialog
        dialog.show();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int langResult = tts.setLanguage(Locale.US);
            if (langResult == TextToSpeech.LANG_MISSING_DATA || langResult == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Língua não suportada ou dados faltando");
            } else {
                btnSpeak.setEnabled(true);
            }
        } else {
            Log.e("TTS", "Falha na inicialização do TextToSpeech");
        }
    }

    private void speakOut() {
        String text = editText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        speechList.add(text);
        adapter.notifyDataSetChanged();

        // Inserir a fala no banco de dados
        long id = falasDAO.inserirFala(text);
    }

    private Button btnViewSpeechList;

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
