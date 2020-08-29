package com.example.h_mal.flavourednewsapp.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.h_mal.flavourednewsapp.data.repositories.RepositoryImpl;

import javax.inject.Inject;

class MainViewModelFactory implements ViewModelProvider.Factory {
    RepositoryImpl repository;

    @Inject
    public MainViewModelFactory(RepositoryImpl repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    @SuppressWarnings("UNCHECKED_CAST")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(repository);
    }
}