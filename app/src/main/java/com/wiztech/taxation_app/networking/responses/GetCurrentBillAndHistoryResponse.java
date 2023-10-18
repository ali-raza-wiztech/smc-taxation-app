package com.wiztech.taxation_app.networking.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCurrentBillAndHistoryResponse {
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{
        @SerializedName("currentBillData")
        private GetCurrentBillData getCurrentBillData;
        @SerializedName("billHistory")
        private List<GetCurrentBillData> billHistory;

        public GetCurrentBillData getGetCurrentBillData() {
            return getCurrentBillData;
        }

        public void setGetCurrentBillData(GetCurrentBillData getCurrentBillData) {
            this.getCurrentBillData = getCurrentBillData;
        }

        public List<GetCurrentBillData> getBillHistory() {
            return billHistory;
        }

        public void setBillHistory(List<GetCurrentBillData> billHistory) {
            this.billHistory = billHistory;
        }
    }
}

