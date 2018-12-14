package com.example.android.mindvalley.mindvalley.loader;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import com.example.android.mindvalley.mindvalley.model.Pin;
import com.example.android.mindvalley.mindvalley.network.PinUtils;

import java.util.List;

public class PinLoader extends AsyncTaskLoader<List<Pin>> {

    private String url;

    public PinLoader(@NonNull Context context, String urlString) {
        super(context);
        url = urlString;

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Pin> loadInBackground() {
        if (url == null){
            return  null;
        }
        List<Pin> pinList = PinUtils.fetchPin(url);
        return pinList;
    }
}
