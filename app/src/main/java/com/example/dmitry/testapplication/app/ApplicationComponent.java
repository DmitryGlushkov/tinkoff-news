package com.example.dmitry.testapplication.app;

import com.example.dmitry.testapplication.ui.Loaders;
import com.example.dmitry.testapplication.ui.fragments.FragmentBase;
import com.example.dmitry.testapplication.ui.views.TextViewEuro;
import com.example.dmitry.testapplication.ui.views.TextViewMedium;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(FragmentBase fragmentBase);

    void inject(TextViewMedium textViewMedium);

    void inject(TextViewEuro textViewEuro);

    void inject(Loaders.LoaderNewsTitles loaderNewsTitles);

    void inject(Loaders.LoaderNewsContent loaderNewsContent);
}
