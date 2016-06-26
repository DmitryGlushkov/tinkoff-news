package com.example.dmitry.testapplication.managers;

import android.content.ContentValues;
import android.content.Context;

import com.example.dmitry.testapplication.db.DbInterface;
import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.example.dmitry.testapplication.models.ModelNewsTitle;
import com.example.dmitry.testapplication.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataManager {

    private DbInterface db;
    private Context context;
    private ArrayList<ModelNewsTitle> newsTitleList = new ArrayList<>();
    private ModelNewsContent newsContent = null;

    public DataManager(Context context) {
        this.context = context;
        this.db = new DataBaseManager(context);
    }

    public ArrayList<ModelNewsTitle> getNewsTitleList() {
        return newsTitleList;
    }

    public ModelNewsContent getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(ModelNewsContent content) {
        newsContent = content;
        if (content != null) db.saveNewsContent(content);
    }

    public void setNewsTitleList(List<ModelNewsTitle> list) {
        Collections.sort(list, new Comparator<ModelNewsTitle>() {
            @Override
            public int compare(ModelNewsTitle lhs, ModelNewsTitle rhs) {
                return lhs.publicationDate > rhs.publicationDate ? -1 : 1;
            }
        });
        newsTitleList.clear();
        newsTitleList.addAll(list);
        db.saveNewsTitleList(newsTitleList);
    }

    public void readNewsTitleListFromDb() {
        newsTitleList.clear();
        newsTitleList.addAll(db.readTitlesFromDataBase());
    }

    public void readNewsContentListFromDb(String id) {
        newsContent = db.readContentFromDataBase(id);
    }

    public boolean offlineMode() {
        return !Utils.isOnline(context);
    }

}
