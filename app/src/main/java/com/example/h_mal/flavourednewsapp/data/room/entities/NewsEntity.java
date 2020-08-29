package com.example.h_mal.flavourednewsapp.data.room.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.h_mal.flavourednewsapp.data.network.model.Article;


@Entity(tableName = "News")
public class NewsEntity {

    public String author;
    public String title;
    public String description;
    @NonNull
    @PrimaryKey(autoGenerate = false)
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;

    public NewsEntity(String author, String title, String description, @NonNull String url, String urlToImage, String publishedAt, String content) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public NewsEntity(Article article) {
        this.author = article.author;
        this.title = article.title;
        this.description = article.description;
        this.url = article.url;
        this.urlToImage = article.urlToImage;
        this.publishedAt = article.publishedAt;
        this.content = article.content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
