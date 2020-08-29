package com.example.h_mal.flavourednewsapp.app;

import android.content.Context;

import com.example.h_mal.flavourednewsapp.data.room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    AppDatabase appDatabase;

    public RoomModule(Context context) {
        appDatabase = AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    AppDatabase providesAppDatabase() {
        return appDatabase;
    }

//    @Singleton
//    @Provides
//    ProductDao providesProductDao(DemoDatabase demoDatabase) {
//        return demoDatabase.getProductDao();
//    }
//
//    @Singleton
//    @Provides
//    ProductRepository productRepository(ProductDao productDao) {
//        return new ProductDataSource(productDao);
//    }

}
