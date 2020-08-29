package com.example.h_mal.flavourednewsapp.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.h_mal.flavourednewsapp.data.network.model.Article;
import com.example.h_mal.flavourednewsapp.data.network.model.NewsResponse;
import com.example.h_mal.flavourednewsapp.data.repositories.RepositoryImpl;
import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;
import com.example.h_mal.flavourednewsapp.utils.Event;

import java.util.List;
import java.util.concurrent.Executors;


public class MainViewModel extends ViewModel {
    @NonNull
    private RepositoryImpl repository;

    public MainViewModel(@NonNull RepositoryImpl repository) {
        this.repository = repository;

    }

    // livedata for user items in room database
    public LiveData<List<NewsEntity>> getNewsLiveData(){
        return  repository.getNewsFromDatabase();
    }

    MutableLiveData<Boolean> operationState = new MutableLiveData<>();
    MutableLiveData<Event<String>> operationError = new MutableLiveData<>();

    public void getNews(String searchTerm){
        // validate that search term is not empty
        if (searchTerm.isEmpty()){
            operationError.postValue(new Event<String>("Enter a valid username"));
            return;
        }

        repository.getNewsFromApi(searchTerm, new RepositoryImpl.AsyncTaskResultListener<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse value) {
                List<Article> articles = value.articles;
                if (!articles.isEmpty()) {
                    // save news articles to database
                    saveResultsToDatabase(articles);
                    // save last search term
                    repository.saveCurrentSearchToPrefs(searchTerm);
                }
            }

            @Override
            public void onFailed(String error) {
                operationError.postValue(new Event<String>(error));
            }
        });

    }

    private void saveResultsToDatabase(List<Article> articles){
        Executors.newSingleThreadScheduledExecutor().submit(() -> repository.saveNewsToDatabase(articles));
    }


    public LiveData<NewsEntity> getSingleNews(String url){
        return repository.getSingleNewsFromDatabase(url);
    }

}