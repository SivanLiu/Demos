package com.example.mpchart;

import com.github.mikephil.charting.charts.LineChart;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.List;

public class CustomLineChart extends LineChart {
    private List<Integer> lineDataIndexs;
    private List<Integer> colors;


    public CustomLineChart(Context context) {
        super(context);
    }

    public CustomLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void initCustomLineChart(List<Integer> lineDataIndexs, List<Integer> colors, int textSize) {
        this.lineDataIndexs = lineDataIndexs;
        this.colors = colors;
        customLineChartRenderer.setDrawText(lineDataIndexs, colors, textSize);
    }

    private CustomLineChartRenderer customLineChartRenderer;

    @Override
    protected void init() {
        super.init();

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        return;
//        customLineChartRenderer = new CustomLineChartRenderer(this, mAnimator, mViewPortHandler);
//        setRenderer(customLineChartRenderer);
    }
}