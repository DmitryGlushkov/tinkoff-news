package com.example.dmitry.testapplication.app;

import com.example.dmitry.testapplication.BuildConfig;
import com.example.dmitry.testapplication.api.HttpService;
import com.example.dmitry.testapplication.db.DbInterface;
import com.example.dmitry.testapplication.managers.DataBaseManager;
import com.example.dmitry.testapplication.managers.DataManager;
import com.example.dmitry.testapplication.models.ModelNewsTitle;
import com.example.dmitry.testapplication.utils.FontHelper;
import com.example.dmitry.testapplication.utils.ModelNewsTitleDeSerializer;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    ApplicationBase applicationBase;

    public ApplicationModule(ApplicationBase applicationBase) {
        this.applicationBase = applicationBase;
    }

    @Provides
    @Singleton
    public HttpService provideHttpService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.server)
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder()
                                .registerTypeAdapter(ModelNewsTitle.class, new ModelNewsTitleDeSerializer())
                                .create()))
                .build().create(HttpService.class);
    }

    @Provides
    @Singleton
    public ApplicationBase provideApplication() {
        return applicationBase;
    }

    @Provides
    @Singleton
    public FontHelper provideFontHelper() {
        return new FontHelper(applicationBase);
    }

    @Provides
    @Singleton
    public DataManager provideDataManager() {
        return new DataManager(applicationBase);
    }

}
