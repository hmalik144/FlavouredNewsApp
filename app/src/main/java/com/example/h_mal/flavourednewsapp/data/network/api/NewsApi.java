package com.example.h_mal.flavourednewsapp.data.network.api;

import com.example.h_mal.flavourednewsapp.data.network.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("v2/everything")
    Call<NewsResponse> getNewsFromApi(@Query("q") String query);

}
