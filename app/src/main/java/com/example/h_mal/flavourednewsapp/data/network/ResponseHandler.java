package com.example.h_mal.flavourednewsapp.data.network;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Response;

public abstract class ResponseHandler {

    public ResponseHandler() {
    }

    public <T> T unwrapResponse(Response<T> response) throws IOException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            String error = response.errorBody().string();
            int code = response.code();
            String errorMessage;
            try {
                errorMessage = new JSONObject(error).getString("error_message");
            } catch (JSONException e) {
                e.printStackTrace();
                errorMessage = "Error Code " + code;
            }

            throw new IOException(errorMessage);
        }
    }


}
