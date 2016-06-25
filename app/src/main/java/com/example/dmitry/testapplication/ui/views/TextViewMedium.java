package com.example.dmitry.testapplication.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.dmitry.testapplication.app.ApplicationBase;
import com.example.dmitry.testapplication.utils.FontHelper;

import javax.inject.Inject;

public class TextViewMedium extends TextView {

    @Inject
    FontHelper fonts;

    public TextViewMedium(Context context) {
        super(context);
        init();
    }

    public TextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public TextViewMedium(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ApplicationBase.injector.inject(this);
        setTypeface(fonts.getTypefaceHelv());
    }
}
