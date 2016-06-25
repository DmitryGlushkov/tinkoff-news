package com.example.dmitry.testapplication.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

    public static String readAsset(Context context, String name) {
        StringBuilder bulder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(name), "UTF-8"));
            String str;
            while ((str = reader.readLine()) != null)
                bulder.append(str);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bulder.toString();
    }

    public static String getDateString(long milliseconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        return sdf.format(calendar.getTime());
    }
}
