package com.example.laucher;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.Locale;

public class AppAdapter extends BaseAdapter implements Filterable {
    public AppAdapter(LauncherActivity launcherActivity, List<AppInfo> loadApps) {
    }

    public AppAdapter(LauncherActivity launcherActivity, java.util.List<AppInfo> loadApps) {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    public Filter getFilter() {
        return new AppFilter();
    }
}
