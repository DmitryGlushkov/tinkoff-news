package com.example.dmitry.testapplication.db;

import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.example.dmitry.testapplication.models.ModelNewsTitle;

import java.util.ArrayList;

public interface DbInterface {

    void saveNewsTitleList(ArrayList<ModelNewsTitle> newsTitles);

    void saveNewsContent(ModelNewsContent content);

    ArrayList<ModelNewsTitle> readTitlesFromDataBase();

    ModelNewsContent readContentFromDataBase(String id);
}
