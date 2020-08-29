package com.example.h_mal.flavourednewsapp.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;

import java.util.List;

@Dao
public abstract class NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void saveAllUsers(List<NewsEntity> news);

    @Query("SELECT * FROM News")
    public abstract LiveData<List<NewsEntity>> getAllUsers();

    // clear database and add new entries
    @Transaction
    public void upsertNewUsers(List<NewsEntity> news){
        deleteEntries();
        saveAllUsers(news);
    }

    @Query("DELETE FROM News")
    public abstract void deleteEntries();

    @Query("SELECT * FROM News WHERE url = :url")
    public abstract LiveData<NewsEntity> getUser(String url);
}
