package com.example.laucher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppListAdapter extends BaseAdapter {

    private Context context;
    private List<AppInfo> appList;

    public AppListAdapter(Context context, List<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_view, parent, false);

            holder = new ViewHolder();
            holder.appIcon = convertView.findViewById(R.id.app_icon);
            holder.appName = convertView.findViewById(R.id.app_name);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AppInfo appInfo = appList.get(position);
        holder.appIcon.setImageDrawable(appInfo.getAppIcon());
        holder.appName.setText(appInfo.getAppName());

        return convertView;
    }

    private static class ViewHolder {
        ImageView appIcon;
        TextView appName;
    }
}
