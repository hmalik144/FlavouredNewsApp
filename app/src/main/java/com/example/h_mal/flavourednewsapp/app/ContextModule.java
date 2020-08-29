package com.example.h_mal.flavourednewsapp.app;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/*
 * Module used for injecting context in ResourcesFile class
 */
@Module
class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }
}
