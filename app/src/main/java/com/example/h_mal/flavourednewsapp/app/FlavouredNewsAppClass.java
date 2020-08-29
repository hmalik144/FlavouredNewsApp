package com.example.h_mal.flavourednewsapp.app;

import android.app.Application;

public class FlavouredNewsAppClass extends Application {

    public ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        // Create dagger2 component to be used in the application
        ContextModule contextModule = new ContextModule(getApplicationContext());

        appComponent = DaggerApplicationComponent.builder()
                .contextModule(contextModule)
                .retrofitModule(new RetrofitModule())
                .roomModule(new RoomModule(contextModule.provideContext()))
                .build();
    }
}
