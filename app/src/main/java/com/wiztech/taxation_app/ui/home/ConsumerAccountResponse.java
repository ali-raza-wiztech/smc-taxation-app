package com.wiztech.taxation_app.ui.home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConsumerAccountResponse {
    @SerializedName("code")
     int code;
    @SerializedName("message")
     String message;
    @SerializedName("data")
     List<ConsumerAccount> data;
}
