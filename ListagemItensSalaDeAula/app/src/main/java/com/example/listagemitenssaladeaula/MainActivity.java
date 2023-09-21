package com.example.listagemitenssaladeaula;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    String [] names = new String[] {"tte", "dfffg", "fgggg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);

        //Recuperar meu data source com fotos e nome
        ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item(1, "Cachorro", R.drawable.cachorro));
        arrayList.add(new Item(2, "Jardim", R.drawable.gardem));
        arrayList.add(new Item(3, "Happy", R.drawable.happy));
        arrayList.add(new Item(4, "Patinho", R.drawable.patinho));
        arrayList.add(new Item(5, "Porquinho", R.drawable.porquinho));

        ItensAdapter adapter = new ItensAdapter(this, R.layout.item_lista, arrayList);

        lv.setAdapter(adapter);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_lista, R.id.textViewnome, names);*/

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = view.findViewById(android.R.id.text1);
                Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
}