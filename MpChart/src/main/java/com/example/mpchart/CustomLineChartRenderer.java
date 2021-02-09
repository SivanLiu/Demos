package com.example.mpchart;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomLineChartRenderer extends LineChartRenderer {
    private static final String TAG = "CustomLineChartRenderer";
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private List<Integer> lineDataIndexs;
    private List<Integer> colors;

    public CustomLineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    public void setDrawText(List<Integer> lineDataIndexs, List<Integer> colors, int textSize) {
        this.lineDataIndexs = lineDataIndexs;
        this.colors = colors;
        textPaint.setTextSize(textSize);
    }

    @Override
    public void drawValues(Canvas c) {
        super.drawValues(c);
        //无须绘制最大小值
        LineData mChartLineData = mChart.getLineData();
        int dataSetCount = mChartLineData.getDataSetCount();
        if (dataSetCount == 0) {
            Log.e(TAG, "dataSetCount is null");
            return;
        }
        if (null == lineDataIndexs || lineDataIndexs.size() == 0) {
            return;
        }

        for (int i = 0; i < mChartLineData.getDataSetCount(); i++) {
            LineDataSet lineDataSet = (LineDataSet) mChartLineData.getDataSetByIndex(i);
            if (null == lineDataSet) {
                continue;
            }
            Transformer transformer = mChart.getTransformer(lineDataSet.getAxisDependency());
            List<Entry> entries = lineDataSet.getValues();
            if (null == entries || entries.isEmpty()) {
                return;
            }
            Entry minEntry = Collections.max(entries, new Comparator<Entry>() {
                @Override
                public int compare(Entry entry, Entry t1) {
                    return (int) (t1.getY() - entry.getY());
                }
            });

            Entry maxEntry = Collections.max(entries, new Comparator<Entry>() {
                @Override
                public int compare(Entry entry, Entry t1) {
                    return (int) (entry.getY() - t1.getY());
                }
            });
            Log.e(TAG, "drawValues max = " + maxEntry.toString() + " min = " + minEntry.toString());
            textPaint.setColor(colors.get(i));
            Rect rectTextBounds = new Rect();

            //绘制最大值
            if ("收缩压".equals(lineDataSet.getLabel())) {
                drawText(transformer, c, rectTextBounds, maxEntry, true);
                continue;
            }

            Log.e(TAG, "ssss draw max ");
            if ("舒张压".equals(lineDataSet.getLabel())) {
                if (maxEntry.getY() != minEntry.getY()) {
                    drawText(transformer, c, rectTextBounds, minEntry, false);
                }
                continue;
            }

            if ("心率".equals(lineDataSet.getLabel())) {
                drawText(transformer, c, rectTextBounds, maxEntry, true);
                if (maxEntry.getY() != minEntry.getY()) {
                    drawText(transformer, c, rectTextBounds, minEntry, false);
                }
                return;
            }
        }
    }

    private void drawText(Transformer transformer, Canvas canvas, Rect rectTextBounds, Entry entry, boolean isMax) {
        MPPointD mPointD = transformer.getPixelForValues(entry.getX(), entry.getY());
        String value = ".52\n889";
//        textPaint.getTextBounds(value, 0, value.length(), rectTextBounds);
        float measureText = textPaint.measureText(value, 0, value.length());
        float height =textPaint.getFontMetrics().top - textPaint.getFontMetrics().bottom;
        int textWidth = (rectTextBounds.right - rectTextBounds.left);
        int textHeight = (rectTextBounds.bottom - rectTextBounds.top);
        Log.e("sss", "textWidth = " + textWidth + " textHeight = " + textHeight + " lenght = " + measureText + " height = "+height);
        float y = isMax ? (float) (mPointD.y - textHeight + 5) : (float) (mPointD.y + textHeight + 5);
        canvas.drawText(String.valueOf((int) entry.getY()), (float) (mPointD.x - textWidth / 2), y, textPaint);
    }
}