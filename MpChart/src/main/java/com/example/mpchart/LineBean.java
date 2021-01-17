package com.example.mpchart;

public class LineBean {
    private String recordTime;
    private int value;

    public LineBean(String recordTime, int value) {
        this.recordTime = recordTime;
        this.value = value;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LineBean{" +
                "recordTime='" + recordTime + '\'' +
                ", value=" + value +
                '}';
    }
}
