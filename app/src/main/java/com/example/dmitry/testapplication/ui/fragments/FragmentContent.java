package com.example.dmitry.testapplication.ui.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.dmitry.testapplication.R;
import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.example.dmitry.testapplication.ui.Loaders;
import com.example.dmitry.testapplication.ui.views.CustomWebView;
import com.example.dmitry.testapplication.utils.Utils;

public class FragmentContent extends FragmentBase implements LoaderManager.LoaderCallbacks<ModelNewsContent> {

    TextView tvTitle, tvDate;
    CustomWebView webContent;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        webContent = (CustomWebView) view.findViewById(R.id.webContent);
        tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        tvDate = (TextView) view.findViewById(R.id.tvDate);
        setVisibility(View.INVISIBLE);
        webContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideIn();
            }
        });
    }

    private void setVisibility(int visibility) {
        tvTitle.setVisibility(visibility);
        webContent.setVisibility(visibility);
        tvDate.setVisibility(visibility);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupContent();
    }

    @Override
    public Loader<ModelNewsContent> onCreateLoader(int id, Bundle args) {
        return new Loaders.LoaderNewsContent(getActivity(), args.getString(Loaders.ID));
    }

    @Override
    public void onLoadFinished(Loader<ModelNewsContent> loader, ModelNewsContent data) {
        setupContent();
    }

    @Override
    public void onLoaderReset(Loader<ModelNewsContent> loader) {

    }

    public void loadContent(String id) {
        progressBar.setVisibility(View.VISIBLE);
        Bundle args = new Bundle();
        args.putString(Loaders.ID, id);
        getActivity().getSupportLoaderManager().restartLoader(Loaders.ID_NEWS_CONTENT, args, this).forceLoad();
    }

    private void setupContent() {
        ModelNewsContent content = dataManager.getNewsContent();
        if (content != null) {
            tvTitle.setText(Html.fromHtml(content.title.text));
            tvDate.setText(Utils.getDateString(content.title.publicationDate));
            webContent.stopLoading();
            webContent.loadData(content.content);
        }
    }

    public void hideOut() {
        setVisibility(View.INVISIBLE);
        dataManager.setNewsContent(null);
    }

    private void hideIn() {

        progressBar.setVisibility(View.GONE);

        tvTitle.setAlpha(0f);
        webContent.setAlpha(0f);

        setVisibility(View.VISIBLE);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(tvTitle, View.ALPHA, 1f),
                ObjectAnimator.ofFloat(webContent, View.ALPHA, 1f)
        );
        set.setDuration(300);
        set.start();
    }
}
