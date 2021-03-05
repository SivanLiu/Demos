package com.example.rxandroid;

import org.acra.config.CoreConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

import android.content.Context;

import androidx.annotation.NonNull;

public class MyReportSenderFactory implements ReportSenderFactory {
    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull CoreConfiguration config) {
        return new LocalReportSender(context);
    }

    @Override
    public boolean enabled(@NonNull CoreConfiguration config) {
        return true;
    }
}