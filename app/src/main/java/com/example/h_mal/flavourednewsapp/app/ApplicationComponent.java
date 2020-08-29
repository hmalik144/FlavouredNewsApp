package com.example.h_mal.flavourednewsapp.app;


import com.example.h_mal.flavourednewsapp.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * create dagger2 interface for dependency injection
 * define Context module to be used later in dependency injection
 */
@Singleton
@Component(modules = {ContextModule.class, RetrofitModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}

