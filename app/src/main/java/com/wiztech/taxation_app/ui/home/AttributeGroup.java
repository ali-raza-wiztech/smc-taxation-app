package com.wiztech.taxation_app.ui.home;

import com.google.gson.annotations.SerializedName;

public class AttributeGroup {
    @SerializedName("active")
    boolean active;
    @SerializedName("processedBy")
    String processedBy;
    @SerializedName("processedAt")
    String processedAt;
    @SerializedName("id")
    int  id;
    @SerializedName("title")
    String title;
    @SerializedName("warningLimit")
    int warningLimit;
    @SerializedName("disconnectionLimit")
    int disconnectionLimit;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    public String getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(String processedAt) {
        this.processedAt = processedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWarningLimit() {
        return warningLimit;
    }

    public void setWarningLimit(int warningLimit) {
        this.warningLimit = warningLimit;
    }

    public int getDisconnectionLimit() {
        return disconnectionLimit;
    }

    public void setDisconnectionLimit(int disconnectionLimit) {
        this.disconnectionLimit = disconnectionLimit;
    }
}
