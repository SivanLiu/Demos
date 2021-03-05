package com.example.rxandroid;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraDialog;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.data.StringFormat;
import org.acra.file.Directory;

import android.app.Application;
import android.content.Context;
@AcraDialog(resText = R.string.crash, resCommentPrompt =R.string.crash)
@AcraCore(buildConfigClass = BuildConfig.class, reportSenderFactoryClasses = MyReportSenderFactory.class)
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this)
                .setBuildConfigClass(BuildConfig.class)
                .setReportFormat(StringFormat.JSON)
                .setApplicationLogFileDir(Directory.EXTERNAL_STORAGE)
                .setApplicationLogFile("crash_log.log");
        builder.setEnabled(true);

//        builder.getPluginConfigurationBuilder(HttpSenderConfigurationBuilder.class)
//                .setUri("https://yourdomain.com/acra/report")
//                .setHttpMethod(HttpSender.Method.POST)
//                .setBasicAuthLogin("*****")
//                .setBasicAuthPassword("*****")
//                .setEnabled(true);
//        builder.getPluginConfigurationBuilder(SchedulerConfigurationBuilder.class)
//                .setRequiresNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
//                .setRequiresBatteryNotLow(true)
//                .setEnabled(true);
//        builder.getPluginConfigurationBuilder(LimiterConfigurationBuilder.class)
//                .setEnabled(true);
        ACRA.init(this, builder);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}