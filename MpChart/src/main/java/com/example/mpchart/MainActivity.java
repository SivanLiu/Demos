package com.example.mpchart;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineChart = findViewById(R.id.line_chart);
//        LineChartUtils.initLineChart(mLineChart, LineChartUtils.getMonthValueForMatter("2020-12"));
//        mLineChart.getXAxis().setAxisMinimum(0);
//        mLineChart.getXAxis().setAxisMaximum(1440);
        LineChartUtils.initLineChart(mLineChart, LineChartUtils.getDayValueForMatter());
        mLineChart.setMaxVisibleValueCount(10000);
        mLineChart.getAxisLeft().setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        mLineChart.getAxisRight().setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
//        mLineChart.getXAxis().setLabelCount(7);
        mLineChart.getXAxis().setLabelRotationAngle(75);
//        mLineChart.getXAxis().setGranularityEnabled(true);
        mLineChart.getXAxis().setGranularity(1);
        LineData heartRateData = getLineHeartRateData(preProcessHeartRateData(getData()), Color.parseColor("#18B4FF"), ContextCompat.getDrawable(this, R.drawable.line_chart_circle));
        mLineChart.setData(heartRateData);
        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.e("sss", "select = " + e.toString());
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private static LineData getLineHeartRateData(List<LineBean> heartRateBeans, int color, Drawable icon) {
        List<Entry> entries = new ArrayList<>();
        List<Entry> emptyEntries = new ArrayList<>();
        //收缩压
        for (int i = 0; i < heartRateBeans.size(); i++) {
            LineBean data = heartRateBeans.get(i);
            if (null == data || data.getValue() == 0) {
                Entry emptyEntry = new Entry(i, 0);
                emptyEntries.add(emptyEntry);
            } else {
                Entry entry = new Entry(i, (float) data.getValue(), icon, heartRateBeans);
                entries.add(entry);
            }
        }

        // 每一个LineDataSet代表一条线
        LineDataSet heartRateLineData = new LineDataSet(entries, "心率");
        LineDataSet emptyLineData = new LineDataSet(emptyEntries, "空值");
        LineChartUtils.initHeartRateLineDataSet(heartRateLineData, color);
        LineChartUtils.initEmptyLineDataSet(emptyLineData);
        return new LineData(heartRateLineData, emptyLineData);
    }

    private List<LineBean> getData() {
        List<LineBean> lineBeanList = new ArrayList<>();
        LineBean lineBean0 = new LineBean("2021-01-01 00:00", 66);
        LineBean lineBean00 = new LineBean("2021-01-01 09:23", 47);
        LineBean lineBean000= new LineBean("2021-01-01 16:23", 99);
        LineBean lineBean0000= new LineBean("2021-01-01 23:23", 99);
//        LineBean lineBean1 = new LineBean("2021-01-01 07:23:00", 49);
//        LineBean lineBean2 = new LineBean("2021-01-01 11:09:00", 89);
//        LineBean lineBean3 = new LineBean("2021-01-01 15:21:00", 73);
//        LineBean lineBean4 = new LineBean("2021-01-01 16:21:00", 59);
//        LineBean lineBean5 = new LineBean("2021-01-01 19:21:00", 120);
//        LineBean lineBean6 = new LineBean("2021-01-01 23:21:00", 115);
//        LineBean lineBean7 = new LineBean("2021-01-01 23:56:00", 156);
        lineBeanList.add(lineBean0);
        lineBeanList.add(lineBean00);
        lineBeanList.add(lineBean000);
        lineBeanList.add(lineBean0000);
//        lineBeanList.add(lineBean00);
//        lineBeanList.add(lineBean1);
//        lineBeanList.add(lineBean2);
//        lineBeanList.add(lineBean3);
//        lineBeanList.add(lineBean4);
//        lineBeanList.add(lineBean5);
//        lineBeanList.add(lineBean6);
//        lineBeanList.add(lineBean7);
        return lineBeanList;
    }

    public static List<LineBean> preProcessHeartRateData(List<LineBean> heartRateBeans) {
        //填充表格数据
        List<LineBean> lastData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            LineBean heartRateBean = getHeartRateBeanByDay(heartRateBeans, i);
            lastData.add(heartRateBean);
        }

//        logger.debug("heartRate lastData = {}", lastData);
        System.out.println("sss " + lastData);
        return lastData;
    }

    public static LineBean getHeartRateBeanByDay(List<LineBean> heartRateBeans, int day) {
        LineBean bean = null;
        for (LineBean heartRateBean : heartRateBeans) {
            if (getSeconds(heartRateBean.getRecordTime()) == day) {
                bean = heartRateBean;
                break;
            }
        }

        return bean;
    }

    public static int getDayOfMonth(String time) {
        SimpleDateFormat current = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = current.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = new SimpleDateFormat("dd").format(date1);
        return Integer.parseInt(result);
    }

    public static int getSeconds(String time) {
        SimpleDateFormat current = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = null;
        Date date1 = null;
        long overTime = 0;
        try {
            date1 = current.parse(time);
            date0 = dayFormat.parse(time);
            overTime = date1.getTime() / 1000 / 60 - date0.getTime() / 1000 / 60;
            System.out.println("time = " + time + "  overTime = " + overTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) overTime;
    }
}