package com.huynh.xinh.trader.di.modules;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XinhHuynh on 10/15/2017.
 */

@Module(includes = ApplicationModule.class)
public class NetworkModule {
    private static final long CACHE_TIME = 5 * 24 * 60 * 60; // 5 day
    private static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 M
    private static final String BASE_URL = "https://api.cryptowat.ch";

    @Provides
    @Singleton
    Retrofit getRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient getOkHttpClient(Cache cache, Interceptor interceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Provides
    @Singleton
    Interceptor getInterceptor() {
        return chain -> {
            Request original = chain.request();
            // Customize the request
            Request request = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Cache-Control", String.format("max-age=%d", CACHE_TIME))
                    .build();

            okhttp3.Response response = chain.proceed(request);
            response.cacheResponse();
            return response;
        };
    }

    @Provides
    @Singleton
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }

    @Provides
    @Singleton
    Cache getOkHttpCache(Application context) {
        Cache cache = new Cache(context.getCacheDir(), CACHE_SIZE);
        return cache;
    }

    @Provides
    @Singleton
    Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

}
