package com.example.h_mal.flavourednewsapp.repositories;

import com.example.h_mal.flavourednewsapp.data.network.model.NewsResponse;

import java.io.IOException;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.mock;

class MockCallable <T> implements Call<T> {
    final Class<T> typeParameterClass;

    public MockCallable(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public Response<T> execute() throws IOException {
        T mockResponse = mock(typeParameterClass);
        return Response.success(mockResponse);
    }

    @Override
    public void enqueue(Callback<T> callback) {

    }

    @Override
    public boolean isExecuted() {
        return false;
    }

    @Override
    public void cancel() {

    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public Call<T> clone() {
        return null;
    }

    @Override
    public Request request() {
        return null;
    }

    @Override
    public Timeout timeout() {
        return null;
    }
}
