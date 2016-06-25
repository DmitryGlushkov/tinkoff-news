package com.example.dmitry.testapplication.api.contract;

import com.example.dmitry.testapplication.models.ModelNewsContent;
import com.google.gson.annotations.SerializedName;

public class ResponseNewsContent extends ResponseBase {

    @SerializedName("payload")
    public ModelNewsContent content;
}
