package com.example.rxandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
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
//        final Disposable[] disposable = {null};
//
//        Observable.intervalRange(10, 5, 3, 2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e("sss", "onSubscribe start");
//                disposable[0] = d;
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                Log.e("sss", "onNext " + aLong);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Log.e("sss", "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("sss", "onComplete");
//                disposable[0].dispose();
//            }
//        });
//
//
//        RxView.clicks(btLogin).throttleFirst(5, TimeUnit.SECONDS)
//                .subscribe(new Observer<Unit>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.e(TAG, "onSubscribe: ");
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Unit unit) {
//                        Log.e(TAG, "onNext: ");
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//                        Log.e(TAG, "onError: ");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.e(TAG, "onComplete: ");
//                    }
//                });

        Observable.merge(getJoke().toObservable(), getQQ().toObservable(), getJoke().toObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("sss onSubscribe", d.toString());
                    }

                    @Override
                    public void onNext(@NonNull Object o) {
                        Log.e("sss onNext", o.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("sss onError", e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("sss onComplete", "onComplete");
                    }
                });
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Throwable {
//                        Log.e("sss accept", o.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("sss throwable", throwable.toString());
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Throwable {
//                        Log.e("sss", "onComplete run...");
//                    }
//                });
    }

    private Single<ResultBean<List<JokenBean>>> getJoke() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apiopen.top")
//                .baseUrl("https://api.oioweb.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        Single<ResultBean<List<JokenBean>>> joke = retrofit.create(ApiService.class).getJoke(1, 2, "video");
        return joke;
        //                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<ResultBean<List<JokenBean>>>() {
//                    @Override
//                    public void accept(ResultBean<List<JokenBean>> objectResultBean) throws Throwable {
//                        Log.e("ssss", objectResultBean.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("ssss", throwable.toString());
//                    }
//                });
    }

    private Single<Object> getQQ() {
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("https://api.oioweb.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit2.create(ApiService.class).getQQ(468088615);

//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object objectResultBean) throws Throwable {
//                        Log.e("sss", "getQQ: "+objectResultBean.toString());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Log.e("sss", "throwable: "+throwable.toString());
//
//                    }
//                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}