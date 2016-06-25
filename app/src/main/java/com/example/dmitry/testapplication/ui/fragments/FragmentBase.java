package com.example.dmitry.testapplication.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.dmitry.testapplication.api.HttpService;
import com.example.dmitry.testapplication.app.ApplicationBase;
import com.example.dmitry.testapplication.managers.DataManager;

import javax.inject.Inject;

public abstract class FragmentBase extends Fragment {

    @Inject
    protected HttpService http;

    @Inject
    DataManager dataManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationBase.injector.inject(this);
    }
}
