package com.example.h_mal.flavourednewsapp.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import javax.inject.Inject;


public class PreferenceProvider {
    private final static String LAST_SAVED = "late_saved";
    private final static String NEWS_SAVED = "news_saved";
    SharedPreferences preference;

    @Inject
    public PreferenceProvider(Context context) {
        preference = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveLastSavedAt(String user, Long savedAt) {
        preference.edit().putString(
                NEWS_SAVED,
                user
        ).putLong(
                LAST_SAVED,
                savedAt
        ).apply();
    }

    @Nullable
    public Long getLastSavedAt(String news){

        String savedUser = preference.getString(NEWS_SAVED, null);
        if (savedUser == null){
            return null;
        }
        if (savedUser.equals(news)){
            return preference.getLong(LAST_SAVED, 1595076034403L);
        }
        return null;
    }
}
