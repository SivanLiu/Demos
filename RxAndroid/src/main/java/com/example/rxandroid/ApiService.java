package com.example.rxandroid;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("getJoke/")
    Single<ResultBean<List<JokenBean>>> getJoke(@Query("page") int page, @Query("count") int count, @Query("type") String type);
}