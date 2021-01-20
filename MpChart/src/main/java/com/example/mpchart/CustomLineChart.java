package com.example.mpchart;

import com.github.mikephil.charting.charts.LineChart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class CustomLineChart extends LineChart {
    public CustomLineChart(Context context) {
        super(context);
    }

    public CustomLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();

        //获取屏幕宽度,因为默认是向右延伸显示数字的(如图1),当最值在屏幕右端,屏幕不够显示时要向左延伸(如图2)
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        mRenderer = new CustomLineChartRenderer(this, mAnimator, mViewPortHandler);
    }
}