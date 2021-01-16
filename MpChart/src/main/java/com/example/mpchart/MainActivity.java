package com.example.mpchart;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLineChart = findViewById(R.id.line_chart);
        LineChartUtils.initLineChart(mLineChart, LineChartUtils.getMonthValueForMatter("2020-01"));
        LineData chartData = new LineData();
        List<Entry> entries1 = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();
        List<Entry> entries3 = new ArrayList<>();
        List<Entry> entries4 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 5 == 0) {
                Entry entry1 = new Entry(i, i * 10 + 15);
                entries1.add(entry1);
                Entry entry3 = new Entry(i, i * 10 + 60);
                entries3.add(entry3);
            } else {
                Entry entry2 = new Entry(i, 0);
                entries2.add(entry2);
                Entry entry4 = new Entry(i, 0);
                entries4.add(entry4);
            }
        }

        LineDataSet lineDataSet1 = new LineDataSet(entries1, "");
        LineDataSet lineDataSet2 = new LineDataSet(entries2, "");
        LineDataSet lineDataSet3 = new LineDataSet(entries3, "");
        LineDataSet lineDataSet4 = new LineDataSet(entries4, "");
        LineData lineData = new LineData(lineDataSet1);
        mLineChart.setData(lineData);

//        mLineChart.getXAxis().setLabelCount();
    }
}