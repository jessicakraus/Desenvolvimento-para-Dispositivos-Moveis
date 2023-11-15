package com.example.laucher;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.laucher.AppAdapter;
import com.example.laucher.AppInfo;

public class LauncherActivity extends AppCompatActivity {
    private GridView gridView;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        appAdapter = new AppAdapter(this, loadApps());
        gridView.setAdapter(appAdapter);

        // Configure a barra de pesquisa
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Filtrar aplicativos com base na pesquisa
                appAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filtrar aplicativos à medida que o usuário digita
                appAdapter.getFilter().filter(newText);
                return true;
            }

        });
    }
    private List<AppInfo> loadApps() {
        List<AppInfo> apps = new ArrayList<>();
        PackageManager packageManager = getPackageManager();

        // Obter a lista de aplicativos instalados
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            // Crie um objeto AppInfo para cada aplicativo
            AppInfo appInfo = new AppInfo(
                    packageManager.getApplicationLabel(packageInfo).toString(),
                    packageInfo.packageName,
                    packageInfo.name,
                    packageManager.getApplicationIcon(packageInfo)
            );

            apps.add(appInfo);
        }

        return apps;
    }

}
