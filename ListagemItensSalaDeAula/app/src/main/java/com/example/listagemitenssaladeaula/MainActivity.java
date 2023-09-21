package com.example.listagemitenssaladeaula;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnAdd;
    private ListView listView;
    private ArrayList<String> stringList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.button_Adicionar);
        listView = findViewById(R.id.listView);

        stringList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newString = editText.getText().toString();
                if (!newString.isEmpty()) {
                    stringList.add(newString);
                    adapter.notifyDataSetChanged(); // Atualiza a lista após a adição
                    editText.setText("");
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Remove o item da lista quando ele é clicado
                stringList.remove(position);
                adapter.notifyDataSetChanged(); // Atualiza a lista após a exclusão
            }
        });
    }
}
