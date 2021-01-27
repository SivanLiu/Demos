package com.example.mpchart;

import android.graphics.Color;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineChartUtils {

    public static final String[] weekDays = {"","周一", "周二", "周三", "周四", "周五", "周六", "周日"};

    public static void initLineChart(LineChart lineChart, ValueFormatter valueFormatter) {
        lineChart.setDescription(null);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.getLegend().setEnabled(false);

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawLabels(true);
        lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setTextSize(15);
        lineChart.getXAxis().setTextColor(Color.parseColor("#AFAFAF"));
        lineChart.getXAxis().setAxisLineWidth(2);
        lineChart.getXAxis().setAxisLineColor(Color.parseColor("#1A393939"));
        lineChart.getXAxis().setGranularity(0.1f);
//        lineChart.setExtraOffsets(0, 5, 0, 10);
        lineChart.setExtraBottomOffset(5);
//        lineChart.getAxisLeft().setSpaceBottom(0);
        lineChart.getXAxis().setAvoidFirstLastClipping(true);

        lineChart.getAxisRight().setEnabled(false);

        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisLeft().setTextColor(Color.TRANSPARENT);
        lineChart.getAxisLeft().setDrawLabels(false);
        lineChart.getAxisLeft().setAxisLineColor(Color.TRANSPARENT);
//        lineChart.getXAxis().setValueFormatter(valueFormatter);
        lineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(weekDays));
    }

    public static void initLineChart(LineChart lineChart) {
        lineChart.setDescription(null);
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.getLegend().setEnabled(false);
        lineChart.getLegend().setForm(Legend.LegendForm.LINE);

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawLabels(true);
        lineChart.getXAxis().setDrawAxisLine(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getXAxis().setTextSize(30f);
        lineChart.getXAxis().setTextColor(Color.parseColor("#AFAFAF"));
        lineChart.getXAxis().setAxisLineWidth(2);
        lineChart.getXAxis().setAxisLineColor(Color.parseColor("#1A393939"));
        lineChart.getXAxis().setGranularity(1f);
//        lineChart.setExtraOffsets(0, 5, 0, 10);
        lineChart.setExtraBottomOffset(5);
//        lineChart.getAxisLeft().setSpaceBottom(0);
        lineChart.getXAxis().setAvoidFirstLastClipping(true);

        lineChart.getAxisRight().setEnabled(false);

        lineChart.getAxisLeft().setEnabled(true);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisLeft().setTextColor(Color.TRANSPARENT);
        lineChart.getAxisLeft().setDrawLabels(false);
        lineChart.getAxisLeft().setAxisLineColor(Color.TRANSPARENT);
    }

    /**
     * 1/1 1/5 1/10 1/15 1/20 1/25
     */
    public static ValueFormatter getMonthValueForMatter(String month) {
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Log.e("sss", "getMonthValueForMatter value = " + value);
                String latestValue;
                if (value == 0) {
                    latestValue = getMonth(month) + "/" + (int) (value + 1);
                } else if ((value + 1) % 5 == 0) {
                    latestValue = getMonth(month) + "/" + (int) (value + 1);
                } else {
//                    return String.valueOf((int)value);
                    return "";
                }
//                return String.valueOf((int)value);
                return latestValue;
            }
        };
        return valueFormatter;
    }

    public static ValueFormatter getWeekValueForMatter() {
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Log.e("sss", "getDayValueForMatter value = " + value);
                if (value > 6) {
                    return "";
                }
                return weekDays[(int) value];
            }
        };

        return valueFormatter;
    }

    /**
     * 按照分钟计算：24*60*60*1000=86400000 毫秒
     */
    public static ValueFormatter getDayValueForMatter() {
        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Log.e("ssss", "every value = "+value);
                String latestValue;
                if (value == 0) {
                    latestValue = "00:00";
                } else if (value == 240) {
                    latestValue = "04:00";
                } else if (value == 480) {
                    latestValue = "08:00";
                } else if (value == 720) {
                    latestValue = "12:00";
                } else if (value == 960) {
                    latestValue = "16:00";
                } else if (value == 1200) {
                    latestValue = "20:00";
                } else if (value == 1440) {
                    latestValue = "24:00";
                } else {
                    latestValue = "";
                }
                Log.e("sss", "getDayValueForMatter latestValue = " + latestValue + " " + value);
//                logger.debug("getFormattedValue value = {}, lastValue = {}", value, latestValue);
                return latestValue;

            }
        };
        return valueFormatter;
    }

    public static void setLineChartLimit(LineChart lineChart, float[] limits) {
        for (int i = 0; i < limits.length; i++) {
            LimitLine limitLine;
            if (i == limits.length - 1) {
                limitLine = new LimitLine(limits[i], "");
            } else {
                limitLine = new LimitLine(limits[i], String.valueOf((int) limits[i]));
            }

            lineChart.getAxisLeft().setDrawLimitLinesBehindData(false);

            limitLine.setLineWidth(2f);
            limitLine.enableDashedLine(10f, 3f, 0f);
            limitLine.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            limitLine.setTextSize(20f);
            limitLine.setLineColor(Color.parseColor("#1A393939"));
            limitLine.setTextColor(Color.parseColor("#AFAFAF"));
            lineChart.getAxisLeft().addLimitLine(limitLine);
        }
    }

    public static void initEmptyLineDataSet(LineDataSet lineDataSet) {
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawValues(false);
        //设置渐变线
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setColor(Color.TRANSPARENT);
        lineDataSet.setLineWidth(0f);
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        //设置数据点圆是否为空心,false为实心
        lineDataSet.setDrawCircleHole(true);
    }

    //设置心率折线图
    public static void initHeartRateLineDataSet(LineDataSet lineDataSet) {
        //设置选中渐变线
        lineDataSet.setHighlightEnabled(true);
        lineDataSet.setHighlightLineWidth(2f);
        lineDataSet.setHighLightColor(Color.parseColor("#28C3F2"));
        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawVerticalHighlightIndicator(true);

        // 显示的圆形大小
        lineDataSet.setCircleRadius(2f);
        lineDataSet.setDrawFilled(false);
        lineDataSet.setDrawValues(false);
        lineDataSet.setValueTextSize(5f);
//        lineDataSet.setColor(color);
//        lineDataSet.setColors(Color.RED, Color.BLACK, Color.BLACK);
        lineDataSet.setLineWidth(4f);
        lineDataSet.setCubicIntensity(0.3f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleHoleColor(Color.BLUE);
        lineDataSet.setCircleColors(Color.RED, Color.BLUE, Color.GREEN);
        lineDataSet.setValueTextSize(30f);
        //设置折线图填充,设置其他类型不显示连线？
        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
    }

    public static String getMonth(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date1 = null;
        try {
            date1 = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = new SimpleDateFormat("MM").format(date1);
        if (Integer.parseInt(result) < 10) {
            return result.substring(1);
        }
        return result;
    }
}