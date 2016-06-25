package com.example.dmitry.testapplication.models;

public class ModelNewsContent {

    public String content;

    public ModelNewsTitle title;

    public static ModelNewsContent stub(){
        return new ModelNewsContent();
    }

}
