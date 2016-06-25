package com.example.dmitry.testapplication.api;

import com.example.dmitry.testapplication.api.contract.ResponseNewsContent;
import com.example.dmitry.testapplication.api.contract.ResponseNewsTitles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HttpService {

    @GET("news")
    Call<ResponseNewsTitles> newsTitles();

    @GET("news_content")
    Call<ResponseNewsContent> newsContent(@Query("id") String id);

}
