package com.example.dmitry.testapplication.app;

public class ApplicationBase extends android.app.Application {

    public static ApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
