package com.example.dmitry.testapplication.models;

import android.content.ContentValues;
import android.database.Cursor;

import static com.example.dmitry.testapplication.db.DbContract.*;

public class ModelNewsTitle extends ModelBase {

    public String id;

    public String text;

    public long publicationDate;

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(TableNewsTitle._ID, id);
        cv.put(TableNewsTitle.COLUMN_TEXT, text);
        cv.put(TableNewsTitle.COLUMN_DATE, publicationDate);
        return cv;
    }

    @Override
    public ModelNewsTitle fromCursor(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(TableNewsTitle._ID));
        text = cursor.getString(cursor.getColumnIndex(TableNewsTitle.COLUMN_TEXT));
        publicationDate = cursor.getLong(cursor.getColumnIndex(TableNewsTitle.COLUMN_DATE));
        return this;
    }
}
