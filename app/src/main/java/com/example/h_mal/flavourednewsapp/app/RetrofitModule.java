package com.example.h_mal.flavourednewsapp.app;

import android.content.Context;

import com.example.h_mal.flavourednewsapp.data.network.api.NewsApi;
import com.example.h_mal.flavourednewsapp.data.network.api.interceptors.NetworkConnectionInterceptor;
import com.example.h_mal.flavourednewsapp.data.network.api.interceptors.QueryInterceptor;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Provides
    NewsApi getApiInterface(Retrofit retroFit) {
        return retroFit.create(NewsApi.class);
    }

    @Provides
    Retrofit getRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    OkHttpClient getOkHttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor,
            NetworkConnectionInterceptor networkConnectionInterceptor,
            QueryInterceptor queryInterceptor
    ) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(networkConnectionInterceptor)
                .addInterceptor(queryInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(5 * 60, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    NetworkConnectionInterceptor getNetworkInterceptor(Context context) {
        return new NetworkConnectionInterceptor(context);
    }

    @Provides
    QueryInterceptor getQueryInterceptor() {
        return new QueryInterceptor();
    }

    @Provides
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }
}
