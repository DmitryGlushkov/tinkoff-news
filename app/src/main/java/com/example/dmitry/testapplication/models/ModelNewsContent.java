package com.example.dmitry.testapplication.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.dmitry.testapplication.db.DbContract;

public class ModelNewsContent extends ModelBase {

    public String content;

    public ModelNewsTitle title;

    @Override
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(DbContract.TableNewsContent._ID, title.id);
        cv.put(DbContract.TableNewsContent.COL_CONTENT, content);
        return cv;
    }

    @Override
    public ModelNewsContent fromCursor(Cursor cursor) {
        content = cursor.getString(cursor.getColumnIndex(DbContract.TableNewsContent.COL_CONTENT));
        return this;
    }
}
