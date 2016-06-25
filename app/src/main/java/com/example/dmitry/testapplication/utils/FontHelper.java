package com.example.dmitry.testapplication.utils;

import android.app.Application;
import android.graphics.Typeface;

public class FontHelper {

    Typeface typefaceEuro, typefaceHelveticaMedium;

    public FontHelper(Application application) {
        typefaceEuro = Typeface.createFromAsset(application.getAssets(), "font/eurofurence_light.ttf");
        typefaceHelveticaMedium = Typeface.createFromAsset(application.getAssets(), "font/HelveticaNeueCyr_Medium.otf");
    }

    public Typeface getTypefaceEuro(){
        return typefaceEuro;
    }

    public Typeface getTypefaceHelv(){
        return typefaceHelveticaMedium;
    }
}
