package com.example.listagemdeitenssimplestarefa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonAdicionar;
    private ListView listView;
    private ArrayList<String> itemList;
    private ArrayAdapter<String> adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        editText = findViewById(R.id.editText);
        buttonAdicionar = findViewById(R.id.Button_Adicionar);
        listView = findViewById(R.id.listView);

        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itemList);
        listView.setAdapter(adapter);

        buttonAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            removeItem(position);
        });
    }

    private void addItem() {
        String item = editText.getText().toString().trim();
        if (!item.isEmpty()) {
            itemList.add(item);
            adapter.notifyDataSetChanged();
            editText.setText("");
        }
    }

    private void removeItem(int position) {
        itemList.remove(position);
        adapter.notifyDataSetChanged();
    }
}