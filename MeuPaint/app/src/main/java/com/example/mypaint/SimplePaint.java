package com.example.mypaint;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {
    private final List<Paint> paintList;
    private final List<Path> pathList;
    private Paint currentPaint;
    private Path currentPath;
    private final ColorDrawable currentColor;
    private StyleType style = StyleType.linha;

    float auxiliarLxInicial = 0,
            auxiliarLxFinal = 0,
            auxiliarLyInicial = 0,
            auxiliarLyFinal = 0;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintList = new ArrayList<>();
        pathList = new ArrayList<>();

        currentColor = new ColorDrawable(Color.BLACK);
        initialize();
    }

    private void initialize() {
        currentPaint = new Paint();
        currentPath = new Path();

        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(20);
        currentPaint.setColor(currentColor.getColor());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paintList.size(); i++) {
            canvas.drawPath(pathList.get(i), paintList.get(i));
        }

        switch (style) {
            case linha:
                canvas.drawPath(currentPath, currentPaint);
                break;
            case circulo:
                break;
            case quadrado:
                break;
        }

        canvas.drawPath(currentPath, currentPaint);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ly, lx;

        lx = event.getX();
        ly = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(lx, ly);
                auxiliarLxInicial = lx;
                auxiliarLyInicial = ly;
                break;

            case MotionEvent.ACTION_MOVE:
                auxiliarLxFinal = lx;
                auxiliarLyFinal = ly;
                double distanciaMovendo = Math.sqrt(Math.pow(auxiliarLxFinal - auxiliarLxInicial, 2) + Math.pow(auxiliarLyFinal - auxiliarLyInicial, 2));

                if (style == StyleType.linha) {
                    currentPath.lineTo(lx, ly);
                } else if (style == StyleType.circulo) {
                    float raio = (float) (distanciaMovendo / 2);
                    float x = (auxiliarLxInicial + auxiliarLxFinal) / 2;
                    float y = (auxiliarLyInicial + auxiliarLyFinal) / 2;
                    currentPath.reset();
                    currentPath.addCircle(x, y, raio, Path.Direction.CW);
                } else if (style == StyleType.quadrado) {
                    currentPath.reset();
                    // Garanta que os pontos estejam na ordem correta para desenhar o retângulo
                    float left = Math.min(auxiliarLxInicial, auxiliarLxFinal);
                    float right = Math.max(auxiliarLxInicial, auxiliarLxFinal);
                    float top = Math.min(auxiliarLyInicial, auxiliarLyFinal);
                    float bottom = Math.max(auxiliarLyInicial, auxiliarLyFinal);
                    currentPath.addRect(left, top, right, bottom, Path.Direction.CW);
                }
                break;

            case MotionEvent.ACTION_UP:
                auxiliarLxFinal = lx;
                auxiliarLyFinal = ly;

                if (style == StyleType.linha) {
                    currentPath.lineTo(lx, ly);
                } else if (style == StyleType.circulo) {
                    double distanciaFinal = Math.sqrt(Math.pow(auxiliarLxFinal - auxiliarLxInicial, 2) + Math.pow(auxiliarLyFinal - auxiliarLyInicial, 2));
                    float raio = (float) (distanciaFinal / 2);
                    float x = (auxiliarLxInicial + auxiliarLxFinal) / 2;
                    float y = (auxiliarLyInicial + auxiliarLyFinal) / 2;
                    currentPath.reset();
                    currentPath.addCircle(x, y, raio, Path.Direction.CW);
                } else if (style == StyleType.quadrado) {
                    // Garanta que os pontos estejam na ordem correta para desenhar o retângulo
                    float left = Math.min(auxiliarLxInicial, auxiliarLxFinal);
                    float right = Math.max(auxiliarLxInicial, auxiliarLxFinal);
                    float top = Math.min(auxiliarLyInicial, auxiliarLyFinal);
                    float bottom = Math.max(auxiliarLyInicial, auxiliarLyFinal);
                    currentPath.reset();
                    currentPath.addRect(left, top, right, bottom, Path.Direction.CW);
                }

                paintList.add(currentPaint);
                pathList.add(currentPath);

                initialize();
                break;
            default:
                break;
        }

        invalidate();
        return true;
    }

    public void setColor(Color color) {
        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
    }
    public void backDraw() {
        if (!paintList.isEmpty()) {
            paintList.remove(paintList.size() - 1);
            pathList.remove(pathList.size() - 1);
            invalidate();
        }
    }

    public void removeDraw() {
        paintList.clear();
        pathList.clear();
        invalidate();
    }

    public void setStyleType(StyleType style) {
        this.style = style;
    }
}

enum StyleType {
    linha,
    circulo,
    quadrado,
}
