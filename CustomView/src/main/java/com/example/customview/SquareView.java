package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SquareView extends View {

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int max = Math.max(width, height);
        setMeasuredDimension(max, max);
    }

//    @Override
//    public void layout(int l, int t, int r, int b) {
//        int width = Math.max(r-l,b -t);
//        super.layout(l, t, l+width, t+width);
//    }
}
