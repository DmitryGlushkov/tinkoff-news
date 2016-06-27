package com.example.dmitry.testapplication.ui;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.dmitry.testapplication.api.HttpService;
import com.example.dmitry.testapplication.api.contract.ResponseNewsContent;
import com.example.dmitry.testapplication.api.contract.ResponseNewsTitles;
import com.example.dmitry.testapplication.app.ApplicationBase;
import com.example.dmitry.testapplication.managers.DataManager;
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

    public static class LoaderNewsTitles extends AsyncTaskLoader<ArrayList<ModelNewsTitle>> {

        @Inject
        HttpService http;

        @Inject
        DataManager dataManager;

        public LoaderNewsTitles(Context context) {
            super(context);
            ApplicationBase.injector.inject(this);
        }

        @Override
        public ArrayList<ModelNewsTitle> loadInBackground() {
            if (dataManager.offlineMode()) {
                dataManager.readNewsTitleListFromDb();
            } else {
                try {
                    ResponseNewsTitles responseNewsTitles = http.newsTitles().execute().body();
                    if (responseNewsTitles.isSuccessful()) {
                        dataManager.setNewsTitleList(Arrays.asList(responseNewsTitles.newsArray));
                    } else {
                        dataManager.readNewsTitleListFromDb();
                    }
                } catch (IOException e) {
                    dataManager.readNewsTitleListFromDb();
                    e.printStackTrace();
                }
            }
            return dataManager.getNewsTitleList();
        }
    }

    public static class LoaderNewsContent extends AsyncTaskLoader<ModelNewsContent> {

        @Inject
        HttpService http;

        @Inject
        DataManager dataManager;

        String id;

        public LoaderNewsContent(Context context, String id) {
            super(context);
            ApplicationBase.injector.inject(this);
            this.id = id;
        }

        @Override
        public ModelNewsContent loadInBackground() {
            if (dataManager.offlineMode()) {
                dataManager.readNewsContentListFromDb(id);
            } else {
                try {
                    ResponseNewsContent responseNewsContent = http.newsContent(id).execute().body();
                    if (responseNewsContent.isSuccessful()) {
                        dataManager.setNewsContent(responseNewsContent.content);
                    } else {
                        dataManager.readNewsContentListFromDb(id);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    dataManager.readNewsContentListFromDb(id);
                }
            }
            return dataManager.getNewsContent();
        }
    }


}
