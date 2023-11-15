package com.example.laucher;

import android.widget.Filter;

import java.util.ArrayList;

public class AppFilter extends Filter {
    private List<AppInfo> originalList;  // Lista original de aplicativos
    private List<AppInfo> filteredList;  // Lista filtrada de aplicativos
    private AppAdapter adapter;          // Adaptador associado

    public AppFilter(List<AppInfo> originalList, AppAdapter adapter) {
        this.originalList = originalList;
        this.filteredList = new ArrayList<>(originalList);
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();

        final String filterPattern = constraint.toString().toLowerCase().trim();

        for (AppInfo appInfo : originalList) {
            if (appInfo.getAppName().toLowerCase().contains(filterPattern)) {
                filteredList.add(appInfo);
            }
        }

        FilterResults results = new FilterResults();
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setAppList((List<AppInfo>) results.values);
        adapter.notifyDataSetChanged();
    }
}


