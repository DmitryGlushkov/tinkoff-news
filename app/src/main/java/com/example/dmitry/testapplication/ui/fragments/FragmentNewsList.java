package com.example.dmitry.testapplication.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dmitry.testapplication.R;
import com.example.dmitry.testapplication.adapters.NewsListAdapter;
import com.example.dmitry.testapplication.models.ModelNewsTitle;
import com.example.dmitry.testapplication.ui.Loaders;
import com.example.dmitry.testapplication.ui.activities.MainActivity;

import java.util.ArrayList;

import static com.example.dmitry.testapplication.ui.Loaders.ID_NEWS_TITLES;

public class FragmentNewsList extends FragmentBase implements LoaderManager.LoaderCallbacks<ArrayList<ModelNewsTitle>> {

    NewsListAdapter newsListAdapter;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newsListAdapter = new NewsListAdapter((MainActivity) getActivity(), dataManager.getNewsTitleList());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView newsList = (RecyclerView) view.findViewById(R.id.recycler_view);
        newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsList.setAdapter(newsListAdapter);
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().getSupportLoaderManager().restartLoader(ID_NEWS_TITLES, null, FragmentNewsList.this).forceLoad();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().getSupportLoaderManager().getLoader(ID_NEWS_TITLES) == null)
            getActivity().getSupportLoaderManager().initLoader(ID_NEWS_TITLES, null, this).forceLoad();
    }

    @Override
    public Loader<ArrayList<ModelNewsTitle>> onCreateLoader(int id, Bundle args) {
        return new Loaders.LoaderNewsList(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<ModelNewsTitle>> loader, ArrayList<ModelNewsTitle> data) {
        System.out.println("onLoadFinished " + data.size());
        stopRefreshing();
        dataManager.setNewsTitleList(data);
        newsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<ModelNewsTitle>> loader) {
        stopRefreshing();
    }

    private void stopRefreshing() {
        if (swipeRefresh.isRefreshing()) swipeRefresh.setRefreshing(false);
    }

}
