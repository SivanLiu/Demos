package com.example.rxandroid;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import org.acra.ACRAConstants;
import org.acra.ReportField;
import org.acra.data.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LocalReportSender implements ReportSender {

    private final Map<ReportField, String> mMapping = new HashMap<>();
    private FileWriter crashReport = null;

    public LocalReportSender(Context ctx) {
        File logFile = new File(Environment.getExternalStorageDirectory(), "sdcard/" + ctx.getPackageName() + "/crash.log");

        try {
            Log.e("sss", "Aaaaa");
            Log.e("sss", "path = " + logFile.getAbsolutePath() + logFile.createNewFile());
            crashReport = new FileWriter(logFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean isNull(String aString) {
        return aString == null || ACRAConstants.NULL_VALUE.equals(aString);
    }

    private Map<String, String> remap(CrashReportData report) {

        ReportField[] fields = ACRAConstants.DEFAULT_REPORT_FIELDS;

        final Map<String, String> finalReport = new HashMap<>();
        for (ReportField field : fields) {
            if (mMapping == null || mMapping.get(field) == null) {
                finalReport.put(field.toString(), report.getString(field));
            } else {
                finalReport.put(mMapping.get(field), report.getString(field));
            }
        }
        return finalReport;
    }

    @Override
    public void send(@NonNull Context context, @NonNull CrashReportData crashReportData) throws ReportSenderException {
        final Map<String, String> finalReport = remap(crashReportData);
        File logFile = new File(Environment.getExternalStorageDirectory(), context.getPackageName() + "/crash/crash.log");
        if (!logFile.exists()) {
            logFile.getParentFile().mkdirs();
        }
        try {
            boolean newFile = logFile.createNewFile();
            crashReport = new FileWriter(logFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedWriter buf = new BufferedWriter(crashReport);

            Set<Map.Entry<String, String>> set = finalReport.entrySet();
            Iterator<Map.Entry<String, String>> i = set.iterator();

            while (i.hasNext()) {
                Map.Entry<String, String> me = i.next();
                buf.append("[" + me.getKey() + "]=" + me.getValue());
            }

            buf.flush();
            buf.close();
        } catch (IOException e) {
            Log.e("TAG", "IO ERROR", e);
        }
    }
}