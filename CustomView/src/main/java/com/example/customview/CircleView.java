package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class CircleView extends View {

    private static final int padding = 10;
    private static final int radius = 50;

    Paint paint = new Paint(ANTI_ALIAS_FLAG);

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (padding + radius) * 2;
        int hight = (padding + radius) * 2;

        width = resolveSize(width, widthMeasureSpec);
        hight = resolveSize(hight, heightMeasureSpec);

//        int specWithMode = MeasureSpec.getMode(widthMeasureSpec);
//        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//        switch (specWithMode){
//            case MeasureSpec.EXACTLY:
//                width = specWidthSize;
//                break;
//            case MeasureSpec.AT_MOST:
//                if(width>specWidthSize){
//                    width = specWidthSize;
//                }
//                break;
//            default:
//                break;
//        }

        setMeasuredDimension(width, hight);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(Color.RED);
        canvas.drawCircle(padding + radius, padding + radius, radius, paint);
    }
}
