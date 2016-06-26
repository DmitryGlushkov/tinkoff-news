package com.example.dmitry.testapplication.managers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dmitry.testapplication.db.DbHelper;
import com.example.dmitry.testapplication.db.DbInterface;
import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.example.dmitry.testapplication.models.ModelNewsTitle;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.dmitry.testapplication.db.DbContract.*;

public class DataBaseManager implements DbInterface {

    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "TinkoffNews.db";
    final ExecutorService executorService = Executors.newFixedThreadPool(1);
    SQLiteDatabase dataBase;

    public DataBaseManager(Context context) {
        dataBase = new DbHelper(context, DATABASE_NAME, null, DATABASE_VERSION).getWritableDatabase();
    }

    @Override
    public void saveNewsTitleList(ArrayList<ModelNewsTitle> newsTitles) {
        final ArrayList<ModelNewsTitle> newsTitlesF = new ArrayList<>();
        newsTitlesF.addAll(newsTitles);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                for (ModelNewsTitle newsTitle : newsTitlesF) {
                    ContentValues cv = newsTitle.toContentValues();
                    if (dataBase.update(TableNewsTitle.TABLE_NAME, cv, TableNewsTitle._ID + "=?", new String[]{newsTitle.id}) == 0)
                        dataBase.insert(TableNewsTitle.TABLE_NAME, null, newsTitle.toContentValues());
                }
            }
        });
    }

    @Override
    public void saveNewsContent(ModelNewsContent content) {
        final ModelNewsContent contentF = content;
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                ContentValues cv = contentF.toContentValues();
                if (dataBase.update(TableNewsContent.TABLE_NAME, cv, TableNewsContent._ID + "=?", new String[]{contentF.title.id}) == 0)
                    dataBase.insert(TableNewsContent.TABLE_NAME, null, cv);
            }
        });
    }

    @Override
    public ArrayList<ModelNewsTitle> readTitlesFromDataBase() {
        ArrayList<ModelNewsTitle> result = new ArrayList<>();
        Cursor cursor = dataBase.query(TableNewsTitle.TABLE_NAME, null, null, null, null, null, TableNewsTitle.COLUMN_DATE + " DESC");
        if (cursor != null) {
            while (cursor.moveToNext()) {
                result.add(new ModelNewsTitle().fromCursor(cursor));
            }
            cursor.close();
        }
        return result;
    }

    @Override
    public ModelNewsContent readContentFromDataBase(String id) {
        Cursor cursor = dataBase.query(TableNewsContent.TABLE_NAME, null, TableNewsContent._ID + "=?", new String[]{id}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            ModelNewsContent content = new ModelNewsContent().fromCursor(cursor);
            cursor.close();
            Cursor titleCursor = dataBase.query(TableNewsTitle.TABLE_NAME, null, TableNewsTitle._ID + "=?", new String[]{id}, null, null, null);
            if (titleCursor != null && titleCursor.getCount() > 0) {
                titleCursor.moveToFirst();
                content.title = new ModelNewsTitle().fromCursor(titleCursor);
                titleCursor.close();
                return content;
            }
        }
        return null;
    }
}
