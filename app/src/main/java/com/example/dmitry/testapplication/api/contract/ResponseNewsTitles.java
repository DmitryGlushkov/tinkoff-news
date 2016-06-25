package com.example.dmitry.testapplication.api.contract;

import com.example.dmitry.testapplication.models.ModelNewsTitle;
import com.google.gson.annotations.SerializedName;

public class ResponseNewsTitles extends ResponseBase {

    @SerializedName("payload")
    public ModelNewsTitle[] newsArray;

}
