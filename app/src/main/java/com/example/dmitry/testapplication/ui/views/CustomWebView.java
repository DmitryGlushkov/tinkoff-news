package com.example.dmitry.testapplication.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import com.example.dmitry.testapplication.utils.Utils;

public class CustomWebView extends WebView {

    private String rawEmptyHtml;

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
    }

    public void loadData(String data) {
        loadDataWithBaseURL("file:///android_asset/", getRawEmptyHtml().replace("[_text_]", data), "text/html", "UTF-8", null);
    }

    private String getRawEmptyHtml() {
        if (rawEmptyHtml == null) rawEmptyHtml = Utils.readAsset(getContext(), "html/page.html");
        return rawEmptyHtml;
    }

}
