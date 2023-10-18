package com.wiztech.taxation_app.networking.responses;

import com.google.gson.annotations.SerializedName;

public class GetCurrentBillResponse {

    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    GetCurrentBillData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GetCurrentBillData getData() {
        return data;
    }

    public void setData(GetCurrentBillData data) {
        this.data = data;
    }
}


