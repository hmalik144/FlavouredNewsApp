package com.example.h_mal.flavourednewsapp.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.h_mal.flavourednewsapp.data.room.entities.NewsEntity;

@Database(entities = NewsEntity.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = createDatabase(context);
        }
        return instance;
    }

    private static AppDatabase createDatabase(Context context) {
        return Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "MyDatabase.db"
        ).build();
    }

    public abstract NewsDao getNewsDao();

}
