
package com.example.h_mal.flavourednewsapp.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("source")
    @Expose
    public Source source;
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("urlToImage")
    @Expose
    public String urlToImage;
    @SerializedName("publishedAt")
    @Expose
    public String publishedAt;
    @SerializedName("content")
    @Expose
    public String content;

}
