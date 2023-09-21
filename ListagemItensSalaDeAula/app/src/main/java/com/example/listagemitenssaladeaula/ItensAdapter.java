package com.example.listagemitenssaladeaula;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ItensAdapter extends ArrayAdapter<Item> {
    int mResource;
    public ItensAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflate = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View v = inflate.inflate(mResource, parent, false);

        //Recuperar o item do datasource
        Item item = getItem(position);

        //Recuperar os componentes da View
        TextView tvnome = v.findViewById(R.id.textViewnome);
        ImageView imageView = v.findViewById(R.id.imageView);

        assert item != null;
        tvnome.setText(item.name);
        imageView.setImageResource(item.photo);

        return v;
    }
}