package com.example.dmitry.testapplication.api.contract;

public class ResponseBase {

    private static final String RESULT_OK = "OK";

    public String resultCode;

    public boolean isSuccessful() {
        return resultCode.equals(RESULT_OK);
    }
}
