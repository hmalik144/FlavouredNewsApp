
package com.example.h_mal.flavourednewsapp.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponse {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("totalResults")
    @Expose
    public Integer totalResults;
    @SerializedName("articles")
    @Expose
    public List<Article> articles = null;

}
