package com.example.h_mal.flavourednewsapp.data.network.api.interceptors;

import com.example.h_mal.flavourednewsapp.BuildConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class QueryInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("apiKey", BuildConfig.ParamOne)
                .build();

//        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder().url(url);
        Request request= requestBuilder.build();
        return chain.proceed(request);
    }

}
