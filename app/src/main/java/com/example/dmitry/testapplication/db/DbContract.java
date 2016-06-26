package com.example.dmitry.testapplication.db;

import android.provider.BaseColumns;

public class DbContract {

    public static class TableNewsTitle implements BaseColumns {
        public static final String
                TABLE_NAME = "news_title",
                COLUMN_TEXT = "text",
                COLUMN_DATE = "publicationDate";

        public static String create() {
            return "CREATE TABLE " + TABLE_NAME + " ( " +
                    _ID + " TEXT PRIMARY KEY, " +
                    COLUMN_TEXT + " TEXT, " +
                    COLUMN_DATE + " INTEGER );";
        }

        public static String delete() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
    }

    public static class TableNewsContent implements BaseColumns {
        public static final String
                TABLE_NAME = "news_content",
                COL_CONTENT = "content";

        public static String create() {
            return "CREATE TABLE " + TABLE_NAME + " ( " +
                    _ID + " TEXT PRIMARY KEY, " +
                    COL_CONTENT + " TEXT );";
        }

        public static String delete() {
            return "DROP TABLE IF EXISTS " + TABLE_NAME;
        }
    }
}
