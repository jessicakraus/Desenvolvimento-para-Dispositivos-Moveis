package com.example.camerax;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.Manifest;
import android.os.Environment;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Uri> takePictureLaucher;

    String [] permissions = new String [] {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
    };

    private Uri uriImage;

    public  void  checkPermissoes(){
        //Checando as permissões foram concedidas ou as solicitando ao usuário
        for (String permission: permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(permissions,PackageManager.PERMISSION_GRANTED);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissoes();
        takePictureLaucher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result -> {
            if(result){

                ImageView img = findViewById(R.id.imageView);
                img.setImageURI(uriImage);

            }else{

            }
        });

        findViewById(R.id.bntCamera).setOnClickListener(view -> {
            takePicture();
        });
    }

    public void takePicture(){

        try {

            String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "_";
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
            uriImage = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", imageFile);
            takePictureLaucher.launch(uriImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}