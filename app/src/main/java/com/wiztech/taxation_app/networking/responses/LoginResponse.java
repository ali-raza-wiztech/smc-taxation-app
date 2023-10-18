package com.wiztech.taxation_app.networking.responses;

import com.wiztech.taxation_app.Entities.User;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("data")
    User user;
    @SerializedName("status")
    String status;
    @SerializedName("token")
    String token;
    @SerializedName("errorMessage")
    String errorMessage;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
