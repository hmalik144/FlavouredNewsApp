package com.example.h_mal.flavourednewsapp.data.network.api.interceptors;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkConnectionInterceptor implements Interceptor {
    Context context;

    @Inject
    public NetworkConnectionInterceptor(Context context) {
        this.context = context;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (!isInternetAvailable()){
            throw new IOException("Make sure you have an active data connection");
        }
        return chain.proceed(chain.request());
    }

    private Boolean isInternetAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null){
            return  false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null){
            return false;
        }
        return networkInfo.isConnected();
    }
}
