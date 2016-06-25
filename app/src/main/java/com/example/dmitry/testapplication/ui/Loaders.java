package com.example.dmitry.testapplication.ui;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.dmitry.testapplication.api.HttpService;
import com.example.dmitry.testapplication.api.contract.ResponseNewsContent;
import com.example.dmitry.testapplication.api.contract.ResponseNewsTitles;
import com.example.dmitry.testapplication.app.ApplicationBase;
import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.example.dmitry.testapplication.models.ModelNewsTitle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

public class Loaders {

    public static final int
            ID_NEWS_TITLES = 1,
            ID_NEWS_CONTENT = 2;

    public static final String ID = "id";

    public static class LoaderNewsList extends AsyncTaskLoader<ArrayList<ModelNewsTitle>> {
        @Inject
        HttpService http;

        public LoaderNewsList(Context context) {
            super(context);
            ApplicationBase.injector.inject(this);
        }

        @Override
        public ArrayList<ModelNewsTitle> loadInBackground() {
            ArrayList<ModelNewsTitle> result = new ArrayList<>();
            try {
                ResponseNewsTitles responseNewsTitles = http.newsTitles().execute().body();
                if (responseNewsTitles.isSuccessful()) {
                    result.addAll(Arrays.asList(responseNewsTitles.newsArray));
                } else {
                    // // TODO: 25.06.2016 broadcasr error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    public static class LoaderNewsContent extends AsyncTaskLoader<ModelNewsContent> {

        @Inject
        HttpService http;
        String id;

        public LoaderNewsContent(Context context, String id) {
            super(context);
            ApplicationBase.injector.inject(this);
            this.id = id;
        }

        @Override
        public ModelNewsContent loadInBackground() {
            try {
                ResponseNewsContent responseNewsContent = http.newsContent(id).execute().body();
                if (responseNewsContent.isSuccessful()) {
                    return responseNewsContent.content;
                } else {
                    // // TODO: 25.06.2016 broadcasr error
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ModelNewsContent.stub();
        }
    }


}
