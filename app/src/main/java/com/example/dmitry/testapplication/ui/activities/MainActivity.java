package com.example.dmitry.testapplication.ui.activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.example.dmitry.testapplication.R;
import com.example.dmitry.testapplication.adapters.NewsListAdapter;
import com.example.dmitry.testapplication.utils.Navigation;

public class MainActivity extends AppCompatActivity implements NewsListAdapter.Listener {

    Navigation navigation = new Navigation(this);
    final static long DURATION = 200L;
    BottomSheetBehavior behavior;
    String newsIdLoadTask;
    Toolbar toolbar;

    public NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        nestedScrollView = (NestedScrollView) findViewById(R.id.bottom_sheet);
        nestedScrollView.setClipToPadding(false);
        behavior = BottomSheetBehavior.from(nestedScrollView);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        closeContent();
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        openContent();
                        if (!TextUtils.isEmpty(newsIdLoadTask)) {
                            navigation.displayContent(newsIdLoadTask);
                            newsIdLoadTask = null;
                        }
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeContent();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED)
            toolbar.setTranslationY(-getResources().getDimension(R.dimen.actionbar_size));
    }

    @Override
    protected void onStart() {
        super.onStart();
        navigation.showNewsList();
        navigation.prepareContent();
    }

    @Override
    public void onClickNews(String id) {
        if (behavior.getState() != BottomSheetBehavior.STATE_COLLAPSED) return;
        newsIdLoadTask = id;
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        openContent();
    }

    boolean doExit;

    @Override
    public void onBackPressed() {
        switch (behavior.getState()) {
            case BottomSheetBehavior.STATE_EXPANDED:
                closeContent();
                break;
            default:
                if (doExit) {
                    super.onBackPressed();
                } else {
                    View view = findViewById(R.id.list_container);
                    Snackbar.make(view, getString(R.string.press_again_to_exit), Snackbar.LENGTH_SHORT).show();
                    doExit = true;
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            doExit = false;
                        }
                    }, 2500);
                }
        }
    }

    private void openContent() {
        nestedScrollView.scrollTo(0, 0);
        toolbar.clearAnimation();
        toolbar.animate().translationY(0).setDuration(DURATION).start();
    }

    private void closeContent() {
        nestedScrollView.scrollTo(0, 0);
        navigation.hideContent();
        toolbar.clearAnimation();
        toolbar.animate().translationY(-toolbar.getBottom()).setDuration(DURATION).start();
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
