package com.example.h_mal.flavourednewsapp.data.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.h_mal.flavourednewsapp.data.network.ResponseHandler;
import com.example.h_mal.flavourednewsapp.data.network.api.NewsApi;
import com.example.h_mal.flavourednewsapp.data.network.model.Article;
import com.example.h_mal.flavourednewsapp.data.network.model.NewsResponse;
import com.example.h_mal.flavourednewsapp.data.preferences.PreferenceProvider;
import com.example.h_mal.flavourednewsapp.data.room.AppDatabase;
import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoryImpl extends ResponseHandler implements Repository{
    private static final int MILLISECONDS_ONE_MIN = 60000;

    private NewsApi api;
    private AppDatabase database;
    private PreferenceProvider preference;

    @Inject
    public RepositoryImpl(NewsApi api, AppDatabase database, PreferenceProvider preference) {
        this.api = api;
        this.database = database;
        this.preference = preference;
    }

    // Current list of news in the database
    @Override
    public LiveData<List<NewsEntity>> getNewsFromDatabase() {
        return database.getNewsDao().getAllUsers();
    }

    // retrieving a single news article from an unique url
    @Override
    public LiveData<NewsEntity> getSingleNewsFromDatabase(String url) {
        return database.getNewsDao().getUser(url);
    }

    // save a list of news to the room database
    @Override
    public void saveNewsToDatabase(List<Article> news) {
        List<NewsEntity> newsEntities = new ArrayList<>();
        for (Article article : news){
            newsEntities.add(new NewsEntity(article));
        }
        database.getNewsDao().upsertNewUsers(newsEntities);
    }

    // fetch news from an api call
    @Override
    public void getNewsFromApi(String searchTerm, AsyncTaskResultListener<NewsResponse> asyncTaskResultListener){
        if (isSearchValid(searchTerm)) {
            api.getNewsFromApi(searchTerm).enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    Gson gson = new Gson();

                    Log.i("ApiResponse", gson.toJson(response.body()));
                    asyncTaskResultListener.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    asyncTaskResultListener.onFailed(t.getMessage());
                }
            });

        }
    }

    @Override
    public void saveCurrentSearchToPrefs(String searchTerm) {
        Long time = System.currentTimeMillis();
        preference.saveLastSavedAt(searchTerm, time);
    }

    // boolean response of validity of search
    // if the same search is taking place again with a minute return false
    private Boolean isSearchValid(String searchTerm){
        Long time = preference.getLastSavedAt(searchTerm);
        if (time == null){
            return true;
        }
        Long currentTime = System.currentTimeMillis();
        long difference = currentTime - time;

        return difference > MILLISECONDS_ONE_MIN;
    }

    public interface AsyncTaskResultListener <T>{
        void onSuccess(T value);
        void onFailed(String error);
    }
}
