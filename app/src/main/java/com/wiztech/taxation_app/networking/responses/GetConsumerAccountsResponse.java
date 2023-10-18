package com.wiztech.taxation_app.networking.responses;

import com.wiztech.taxation_app.ui.home.ConsumerAccount;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetConsumerAccountsResponse {
    @SerializedName("code")
    int code;
    @SerializedName("message")
    String message;
    @SerializedName("data")
    GetConsumerAccountsResponse.Data data;


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

    public class Data {
        @SerializedName("totalDues")
        private double totalDues;
        @SerializedName("consumerAccounts")
        private List<ConsumerAccount> billHistory;

        public double getTotalDues() {
            return totalDues;
        }

        public void setTotalDues(double totalDues) {
            this.totalDues = totalDues;
        }

        public List<ConsumerAccount> getBillHistory() {
            return billHistory;
        }

        public void setBillHistory(List<ConsumerAccount> billHistory) {
            this.billHistory = billHistory;
        }
    }
}
