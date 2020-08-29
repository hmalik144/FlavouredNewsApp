package com.example.h_mal.flavourednewsapp.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ViewUtils {

    public static void hide(View view){
        view.setVisibility(View.GONE);
    }

    public static void show(View view){
        view.setVisibility(View.VISIBLE);
    }

    public static void displayToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
