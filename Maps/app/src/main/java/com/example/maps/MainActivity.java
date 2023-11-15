package com.example.maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private LocationManager locationManager;
    private LocationListener locationListener;
    TextView txtLatLong;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialização do LocationManager para gerenciar a localização do dispositivo
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // TextView para exibir a latitude e longitude
        txtLatLong = findViewById(R.id.txViewLatLong);

        // LocationListener para receber atualizações de localização
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                // Atualiza a TextView com a nova localização
                txtLatLong.setText("Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());

                // Adiciona um marcador no mapa com a nova localização
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();  // Remove marcadores antigos
                mMap.addMarker(new MarkerOptions().position(currentLocation).title("Sua Localização"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));  // Move a câmera para a nova localização
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        // Inicialização do mapa assíncrono
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);

        // Verifica permissão de localização
        checkLocationPermission();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Callback chamado quando o mapa estiver pronto
        mMap = googleMap;

        // Configuração inicial do mapa com um marcador de exemplo
        LatLng localizacao = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(localizacao).title("Marcador BR"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacao));
    }

    // Método para verificar e solicitar permissão de localização
    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            startLocationUpdates();
        }
    }

    // Método para iniciar as atualizações de localização
    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

    // Callback chamado após o usuário responder à solicitação de permissão
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            }
        }
    }

    // Método chamado quando a atividade é retomada
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        }
    }

    // Método chamado quando a atividade é pausada
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);  // Remove as atualizações de localização para economizar recursos
    }
}
