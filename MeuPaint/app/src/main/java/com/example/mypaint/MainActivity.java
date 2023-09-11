package com.example.mypaint;

import static android.graphics.Color.valueOf;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    private SimplePaint simplePaint;
    private ImageView imageViewColorPicker;
    private ImageView imageViewVoltar;
    private ImageView imageViewLimpar;
    private ImageView imageViewQuadrado;
    private ImageView imageViewCirculo;
    private ImageView imageViewLinha;
    private ImageView ivColorPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincula os itens do XML com as variáveis Java
        simplePaint = findViewById(R.id.simplePaint);
        imageViewLinha = findViewById(R.id.linha);
        imageViewQuadrado = findViewById(R.id.quadrado);
        imageViewCirculo = findViewById(R.id.circulo);
        imageViewLimpar = findViewById(R.id.limpar);
        imageViewVoltar = findViewById(R.id.voltar);
        ivColorPicker = findViewById(R.id.ivColorPicker);

        // Define um ouvinte de cliques para as visualizações
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.linha:
                        simplePaint.setStyleType(StyleType.linha);
                        break;
                    case R.id.quadrado:
                        simplePaint.setStyleType(StyleType.quadrado);
                        break;
                    case R.id.circulo:
                        simplePaint.setStyleType(StyleType.circulo);
                        break;
                    case R.id.limpar:
                        simplePaint.removeDraw();
                        break;
                    case R.id.voltar:
                        simplePaint.backDraw();
                        break;
                    case R.id.ivColorPicker:
                        colorPickerSelectColor();
                        break;
                }
            }
        };

        // Ações
        imageViewLinha.setOnClickListener(onClickListener);
        imageViewQuadrado.setOnClickListener(onClickListener);
        imageViewCirculo.setOnClickListener(onClickListener);
        imageViewLimpar.setOnClickListener(onClickListener);
        imageViewVoltar.setOnClickListener(onClickListener);
        ivColorPicker.setOnClickListener(onClickListener);
    }

    private void colorPickerSelectColor() {
        // Cria um diálogo de seleção de cor usando a biblioteca de seleção de cores
        new ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setColor(envelope);
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true)
                .attachBrightnessSlideBar(true)
                .setBottomSpace(12)
                .show();
    }

    private void setColor(ColorEnvelope envelope) {
        simplePaint.setColor(valueOf(envelope.getColor()));
        ivColorPicker.setColorFilter(valueOf(envelope.getColor()).toArgb());
    }
}
