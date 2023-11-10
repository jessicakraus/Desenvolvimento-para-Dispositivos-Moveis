package com.example.maps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    LocationManager locationManager;
    GoogleMap mMap;
    TextView txtLatLong;


    private static final int REQUEST_LOCATION_PERMISSION = 1;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLatLong=findViewById(R.id.txViewLatLong);
    }

    LocationListener locationListener= new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            txtLatLong.setText("Latitutlode"+location.getLatitude()+" Longitude:"+location.getLongitude());


        }
    };

    void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão foi concedida, agora você pode prosseguir com a obtenção de localização
            } else {
                // Permissão foi negada, você deve lidar com a negação
            }
        }
    }

    protected void onPause(){
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    protected void onResume(){
        super.onResume();
        checkLocationPermission();
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,1000, 1);
    }

    public void onMapReady(@NonNull, GoogleMap googleMap){
        mMap = googleMap;
        LatLng localizacaoInicial = new LatLng(-23.563059, -20);
        LatLng localizacao = new LatLng(-34, 151); // Use a localização real aqui
        mMap.addMarker(new MarkerOptions().position(localizacao).title("Marcador BR"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacao));
    }
}