package com.example.rxandroid;

import com.jakewharton.rxbinding4.view.RxView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.Unit;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apiopen.top")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        retrofit.create(ApiService.class).getJoke(1, 2, "video")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultBean<List<JokenBean>>>() {
                    @Override
                    public void accept(ResultBean<List<JokenBean>> objectResultBean) throws Throwable {
                        Log.e("ssss", objectResultBean.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.e("ssss", throwable.toString());
                    }
                });


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