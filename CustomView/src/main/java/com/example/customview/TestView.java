package com.example.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TestView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(100, 100, 200, 200, paint);
        canvas.drawCircle(500, 500, 100, paint);
    }
}
