package com.example.sensormanager;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor mSensorLight, mSensorAccelerometer, mSensorGyroscope, mSensorTemperature;
    TextView mTvLight, mTvAccelerometer, mTvGyroscope, mTvTemperature;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvLight = findViewById(R.id.tvSensorLight);
        mTvAccelerometer = findViewById(R.id.tvSensorAccelerometer);
        mTvGyroscope = findViewById(R.id.tvSensorGyroscope);
        mTvTemperature = findViewById(R.id.tvSensorTemperature);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // Sensor de luz
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mSensorLight == null) {
            mTvLight.setText("Este dispositivo não possui um sensor de luz.");
        }

        // Sensor de acelerômetro
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (mSensorAccelerometer == null) {
            mTvAccelerometer.setText("Este dispositivo não possui um sensor de acelerômetro.");
        }

        // Sensor de giroscópio
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mSensorGyroscope == null) {
            mTvGyroscope.setText("Este dispositivo não possui um sensor de giroscópio.");
        }

        // Sensor de temperatura (se disponível)
        mSensorTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (mSensorTemperature == null) {
            mTvTemperature.setText("Este dispositivo não possui um sensor de temperatura.");
        }
    }

    protected void onResume() {
        super.onResume();
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGyroscope != null) {
            mSensorManager.registerListener(this, mSensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorTemperature != null) {
            mSensorManager.registerListener(this, mSensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        float[] values = event.values;

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                mTvLight.setText("Light: " + values[0]);
                break;
            case Sensor.TYPE_ACCELEROMETER:
                mTvAccelerometer.setText("Acelerômetro: \nX: " + values[0] + "\nY: " + values[1] + "\nZ: " + values[2]);
                break;
            case Sensor.TYPE_GYROSCOPE:
                mTvGyroscope.setText("Giroscópio: \nX: " + values[0] + "\nY: " + values[1] + "\nZ: " + values[2]);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTvTemperature.setText("Temperatura: " + values[0] + " °C");
                break;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
