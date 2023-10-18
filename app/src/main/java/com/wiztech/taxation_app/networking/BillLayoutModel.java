package com.wiztech.taxation_app.networking;

import com.google.gson.annotations.SerializedName;

public class BillLayoutModel {
    @SerializedName("active")
    boolean active;
    @SerializedName("processedBy")
     String processedBy;
    @SerializedName("processedAt")
    String processedAt;
    @SerializedName("id")
    int id;
    @SerializedName("templateName")
    String templateName;
    @SerializedName("paramLimit")
    int paramLimit;
    @SerializedName("billTitle")
    String   billTitle;
    @SerializedName("deptTitle")
    String    deptTitle;
    @SerializedName("website")
    String    website;
    @SerializedName("titleMessage")
    String    titleMessage;
    @SerializedName("logoPrimary")
    String    logoPrimary;
    @SerializedName("logoSecondary")
    String    logoSecondary;
    @SerializedName("advertisementImage")
    String advertisementImage;
    @SerializedName("advertisementIncluded")
    boolean   advertisementIncluded;
    @SerializedName("noteNormal")
    String noteNormal;
    @SerializedName("noteBold")
    String    noteBold;
    @SerializedName("warningMessage")
    String    warningMessage;
    @SerializedName("noWarningMessage")
    String    noWarningMessage;
    @SerializedName("noAddMessage")
    String    noAddMessage;
    @SerializedName("penaltyPercentage")
    double   penaltyPercentage;
    @SerializedName("dueDayOfMonth")
    int dueDayOfMonth;

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

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public int getParamLimit() {
        return paramLimit;
    }

    public void setParamLimit(int paramLimit) {
        this.paramLimit = paramLimit;
    }

    public String getBillTitle() {
        return billTitle;
    }

    public void setBillTitle(String billTitle) {
        this.billTitle = billTitle;
    }

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTitleMessage() {
        return titleMessage;
    }

    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    public String getLogoPrimary() {
        return logoPrimary;
    }

    public void setLogoPrimary(String logoPrimary) {
        this.logoPrimary = logoPrimary;
    }

    public String getLogoSecondary() {
        return logoSecondary;
    }

    public void setLogoSecondary(String logoSecondary) {
        this.logoSecondary = logoSecondary;
    }

    public String getAdvertisementImage() {
        return advertisementImage;
    }

    public void setAdvertisementImage(String advertisementImage) {
        this.advertisementImage = advertisementImage;
    }

    public boolean isAdvertisementIncluded() {
        return advertisementIncluded;
    }

    public void setAdvertisementIncluded(boolean advertisementIncluded) {
        this.advertisementIncluded = advertisementIncluded;
    }

    public String getNoteNormal() {
        return noteNormal;
    }

    public void setNoteNormal(String noteNormal) {
        this.noteNormal = noteNormal;
    }

    public String getNoteBold() {
        return noteBold;
    }

    public void setNoteBold(String noteBold) {
        this.noteBold = noteBold;
    }

    public String getWarningMessage() {
        return warningMessage;
    }

    public void setWarningMessage(String warningMessage) {
        this.warningMessage = warningMessage;
    }

    public String getNoWarningMessage() {
        return noWarningMessage;
    }

    public void setNoWarningMessage(String noWarningMessage) {
        this.noWarningMessage = noWarningMessage;
    }

    public String getNoAddMessage() {
        return noAddMessage;
    }

    public void setNoAddMessage(String noAddMessage) {
        this.noAddMessage = noAddMessage;
    }

    public double getPenaltyPercentage() {
        return penaltyPercentage;
    }

    public void setPenaltyPercentage(double penaltyPercentage) {
        this.penaltyPercentage = penaltyPercentage;
    }

    public int getDueDayOfMonth() {
        return dueDayOfMonth;
    }

    public void setDueDayOfMonth(int dueDayOfMonth) {
        this.dueDayOfMonth = dueDayOfMonth;
    }
}
