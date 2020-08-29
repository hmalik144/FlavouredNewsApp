package com.example.h_mal.flavourednewsapp.repositories;

import com.example.h_mal.flavourednewsapp.data.network.api.NewsApi;
import com.example.h_mal.flavourednewsapp.data.network.model.NewsResponse;
import com.example.h_mal.flavourednewsapp.data.preferences.PreferenceProvider;
import com.example.h_mal.flavourednewsapp.data.repositories.RepositoryImpl;
import com.example.h_mal.flavourednewsapp.data.room.AppDatabase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RepositoryImplTest {

    @Mock
    NewsApi api;
    @Mock
    AppDatabase db;
    @Mock
    PreferenceProvider prefs;

    RepositoryImpl repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new RepositoryImpl(api, db, prefs);
    }

    @Test
    public void fetchUserFromApi_positiveResponse() {
        // GIVEN
        String input = "12345";
        NewsResponse mockApiResponse = mock(NewsResponse.class);
        Call<NewsResponse> mockResponse = new MockCallable<>(NewsResponse.class);

        // WHEN
        Mockito.when(api.getNewsFromApi(input)).thenReturn(mockResponse);
        Mockito.when(prefs.getLastSavedAt(input)).thenReturn(null);

        // THEN
        repository.getNewsFromApi(input, new RepositoryImpl.AsyncTaskResultListener<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse value) {
                assertEquals(mockApiResponse, value);
            }

            @Override
            public void onFailed(String error) { }
        });
    }

    @Test
    public void  fetchUserFromApi_negativeResponse() throws IOException {
        // GIVEN
        String input = "12345";
        String errorString = "error";
        Call<NewsResponse> mockResponse = new Call<NewsResponse>(){
            @Override
            public Response<NewsResponse> execute() throws IOException {
                throw new IOException(errorString);
            }
            @Override
            public void enqueue(Callback<NewsResponse> callback) { }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() { }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<NewsResponse> clone() {
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
        };

        // WHEN
        Mockito.when(api.getNewsFromApi(input)).thenReturn(mockResponse);
        Mockito.when(prefs.getLastSavedAt(input)).thenReturn(null);

        // THEN
        repository.getNewsFromApi(input, new RepositoryImpl.AsyncTaskResultListener<NewsResponse>() {
            @Override
            public void onSuccess(NewsResponse value) {
            }

            @Override
            public void onFailed(String error) {
                assertEquals(error, errorString);
            }
        });
    }
}