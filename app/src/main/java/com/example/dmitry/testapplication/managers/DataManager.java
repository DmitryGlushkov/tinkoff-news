package com.example.dmitry.testapplication.managers;

import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.example.dmitry.testapplication.models.ModelNewsTitle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DataManager {

    private ArrayList<ModelNewsTitle> newsTitleList = new ArrayList<>();
    private ModelNewsContent newsContent = null;

    public ArrayList<ModelNewsTitle> getNewsTitleList() {
        return newsTitleList;
    }

    public ModelNewsContent getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(ModelNewsContent content) {
        newsContent = content;
    }

    public void setNewsTitleList(ArrayList<ModelNewsTitle> list) {
        Collections.sort(list, new Comparator<ModelNewsTitle>() {
            @Override
            public int compare(ModelNewsTitle lhs, ModelNewsTitle rhs) {
                return lhs.publicationDate > rhs.publicationDate ? -1 : 1;
            }
        });
        newsTitleList.clear();
        newsTitleList.addAll(list);
    }

}
