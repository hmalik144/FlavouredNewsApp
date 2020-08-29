package com.example.h_mal.flavourednewsapp.data.repositories;

import androidx.lifecycle.LiveData;

import com.example.h_mal.flavourednewsapp.data.network.model.Article;
import com.example.h_mal.flavourednewsapp.data.network.model.NewsResponse;
import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;

import java.util.List;

public interface Repository {

    LiveData<List<NewsEntity>> getNewsFromDatabase();
    LiveData<NewsEntity> getSingleNewsFromDatabase(String url);
    void saveNewsToDatabase(List<Article> news);
    void getNewsFromApi(String searchTerm, RepositoryImpl.AsyncTaskResultListener<NewsResponse> asyncTaskResultListener);
//    NewsResponse getNewsFromApi(String searchTerm) throws IOException;
    void saveCurrentSearchToPrefs(String searchTerm);
}
