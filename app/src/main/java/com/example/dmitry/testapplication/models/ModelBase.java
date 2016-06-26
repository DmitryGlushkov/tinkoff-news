package com.example.dmitry.testapplication.models;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class ModelBase {

    public abstract ContentValues toContentValues();

    public abstract ModelBase fromCursor(Cursor cursor);
}
