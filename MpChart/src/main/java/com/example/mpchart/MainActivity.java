package com.example.mpchart;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private CustomLineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLineChart = findViewById(R.id.line_chart);
//        LineChartUtils.initLineChart(mLineChart, LineChartUtils.getMonthValueForMatter("2020-12"));
        mLineChart.getXAxis().setAxisMinimum(0);
        mLineChart.getXAxis().setAxisMaximum(50);
//        mLineChart.getXAxis().setLabelCount(11);
        mLineChart.getXAxis().setGranularity(1);
        mLineChart.getXAxis().setGranularityEnabled(true);
        LineChartUtils.initLineChart(mLineChart, LineChartUtils.getWeekValueForMatter());
        mLineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
//        mLineChart.getXAxis().setLabelRotationAngle(75);
//        LineData heartRateData = getLineHeartRateData(preProcessHeartRateData(getData()), Color.parseColor("#18B4FF"), ContextCompat.getDrawable(this, R.drawable.line_chart_circle));
        List<Entry> entries = new ArrayList<>();
        Entry entry0 = new Entry(0, 90, 90);
//        Entry entry = new Entry(1, 30, 30);
        Entry entry2 = new Entry(2, 60, 70);
        Entry entry3 = new Entry(3, 60, 90);
        Entry entry5 = new Entry(4, 60, 90);
        Entry entry6 = new Entry(5, 60, 90);
        entries.add(entry0);
//        entries.add(entry);
        entries.add(entry2);
        entries.add(entry3);
        entries.add(entry5);
        entries.add(entry6);
        LineDataSet lineDataSet = new LineDataSet(entries, "");
        LineChartUtils.initHeartRateLineDataSet(lineDataSet);
        mLineChart.setData(new LineData(lineDataSet));


        mLineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.e("sss", "select = " + e.toString());
//                mLineChart.highlightValue(null);
            }

            @Override
            public void onNothingSelected() {

            }
        });
//        mLineChart.clearValues();
        mLineChart.invalidate();
    }

    private static LineData getLineHeartRateData(List<LineBean> heartRateBeans, int color, Drawable icon) {
        List<Entry> entries = new ArrayList<>();
        Entry entry = new Entry(1, 30, icon, heartRateBeans);
        Entry entry2 = new Entry(2, 60, icon, heartRateBeans);
        Entry entry3 = new Entry(3, 60, icon, heartRateBeans);
        entries.add(entry);
        entries.add(entry2);
        entries.add(entry3);
        //收缩压
//        for (int i = 0; i < 10; i++) {
////            LineBean data = heartRateBeans.get(i);
////            if (null == data || data.getValue() == 0) {
////                Entry emptyEntry = new Entry(i, 0);
////                emptyEntries.add(emptyEntry);
////            } else {
////                Entry entry = new Entry(i, (float) data.getValue(), icon, heartRateBeans);
////                entries.add(entry);
////            }
//            if (i == 0 ) {
//                Entry entry = new Entry(i, i * 10, icon, heartRateBeans);
//                entries.add(entry);
//            }
//        }

        // 每一个LineDataSet代表一条线
        LineDataSet heartRateLineData = new LineDataSet(entries, "心率");
//        LineDataSet emptyLineData = new LineDataSet(emptyEntries, "空值");
        LineChartUtils.initHeartRateLineDataSet(heartRateLineData);
//        LineChartUtils.initEmptyLineDataSet(emptyLineData);
        return new LineData(heartRateLineData);
    }

    private List<LineBean> getData() {
        List<LineBean> lineBeanList = new ArrayList<>();
        LineBean lineBean0 = new LineBean("2021-01-01 00:00", 66);
        LineBean lineBean00 = new LineBean("2021-01-01 04:00", 47);
        LineBean lineBean000 = new LineBean("2021-01-01 08:00", 99);
        LineBean lineBean0000 = new LineBean("2021-01-01 24:00", 99);
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
        for (int i = 0; i < 10; i++) {
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