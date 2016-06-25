package com.example.dmitry.testapplication.utils;

import android.support.v7.app.AppCompatActivity;

import com.example.dmitry.testapplication.R;
import com.example.dmitry.testapplication.ui.fragments.FragmentContent;
import com.example.dmitry.testapplication.ui.fragments.FragmentNewsList;

public class Navigation {

    FragmentNewsList fragmentNewsList = new FragmentNewsList();
    FragmentContent fragmentContent = new FragmentContent();

    AppCompatActivity activity;

    public Navigation(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void showNewsList() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_container, fragmentNewsList)
                .commit();
    }

    public void prepareContent(){
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_container, fragmentContent)
                .commit();
    }

    public void displayContent(String id){
        fragmentContent.loadContent(id);
    }

    public void hideContent(){
        fragmentContent.hideOut();
    }

}
