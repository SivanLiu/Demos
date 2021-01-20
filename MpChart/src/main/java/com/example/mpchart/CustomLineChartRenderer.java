package com.example.mpchart;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomLineChartRenderer extends LineChartRenderer {

    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomLineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
        textPaint.setColor(Color.parseColor("#48D2EF"));
        textPaint.setTextSize(20);
    }


    @Override
    public void drawValues(Canvas c) {
        super.drawValues(c);
        LineDataSet dataSet = (LineDataSet) mChart.getLineData().getDataSetByIndex(0);
        Transformer trans = mChart.getTransformer(dataSet.getAxisDependency());
        List<Entry> entries = dataSet.getValues();
        Log.e("sss", "drawValues: 1111 " + entries);
        Collections.sort(entries, new Comparator<Entry>() {
            @Override
            public int compare(Entry entry, Entry t1) {
                return (int) (entry.getY() - t1.getY());
            }
        });
        Log.e("sss", "drawValues: 2222 " + entries);

        Entry minEntry = entries.get(0);
        Entry maxEntry = entries.get(entries.size()-1);

        Log.e("sss", "drawValues: 333 " + maxEntry.toString() + " minEntry = " + minEntry.toString());
        MPPointD minPointD = trans.getPixelForValues(minEntry.getX(), minEntry.getY());
        MPPointD maxPointD = trans.getPixelForValues(maxEntry.getX(), maxEntry.getY());
        c.drawText(String.valueOf(minEntry.getY()), (float) minPointD.x-20, (float) (minPointD.y + 20), textPaint);
        c.drawText(String.valueOf(maxEntry.getY()), (float) maxPointD.x-20, (float) (maxPointD.y - 10), textPaint);
//        Paint paintDrawPointFill = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paintDrawPointFill.setStyle(Paint.Style.FILL);
//        paintDrawPointFill.setColor(Color.WHITE);
//        c.drawCircle((float) pointD.x, (float) pointD.y, 6, paintDrawPointFill);

//        String textTag = "文字内容";
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setTextSize(12);
//        paint.setColor(Color.RED);
//        Rect rectTextBounds = new Rect();
//        paint.getTextBounds(textTag, 0, textTag.length(), rectTextBounds);
//        int textWidth = (rectTextBounds.right - rectTextBounds.left);
//        int textHeight = (rectTextBounds.bottom - rectTextBounds.top);
//
//        RectF rectF = new RectF((int) 5 - 5,
//                (int) 5 - textHeight - 5,
//                (int) 5 + textWidth + 5,
//                (int) 5 + 5);
//
//        c.drawRoundRect(rectF, 5, 5, paint);
    }
}