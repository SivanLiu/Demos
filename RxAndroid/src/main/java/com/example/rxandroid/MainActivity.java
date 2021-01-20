package com.example.rxandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import kotlin.Unit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.tv_test)
    TextView tvTest;
    @BindView(R.id.login)
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onClick: ");
            }
        });


        Log.e("sss", "single start");
//        Single.just("sss").delay(5, TimeUnit.SECONDS).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Throwable {
//                Log.e("sss", "single end " + s);
//            }
//        });

        final Disposable[] disposable = {null};

        Observable.intervalRange(10, 5, 3, 2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("sss", "onSubscribe start");
                disposable[0] = d;
            }

            @Override
            public void onNext(@NonNull Long aLong) {
                Log.e("sss", "onNext " + aLong);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("sss", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("sss", "onComplete");
                disposable[0].dispose();
            }
        });


        RxView.clicks(btLogin).throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(@NonNull Unit unit) {
                        Log.e(TAG, "onNext: ");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}