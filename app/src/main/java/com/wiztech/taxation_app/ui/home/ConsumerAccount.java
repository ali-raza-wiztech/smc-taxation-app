package com.wiztech.taxation_app.ui.home;

import com.google.gson.annotations.SerializedName;

public class ConsumerAccount {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("formNumber")
    String formNumber;
    @SerializedName("issueDate")
    String   issueDate;
    @SerializedName("status")
    boolean  status;
    @SerializedName("remarks")
    String remarks;
    @SerializedName("govtEmployee")
    boolean govtEmployee;
    @SerializedName("challanPaid")
    boolean challanPaid;
    @SerializedName("challanAmountPaid")
    double challanAmountPaid;
    @SerializedName("serviceType")
    String serviceType;

    @SerializedName("attributeGroup")
    AttributeGroup attributeGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormNumber() {
        return formNumber;
    }

    public void setFormNumber(String formNumber) {
        this.formNumber = formNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isGovtEmployee() {
        return govtEmployee;
    }

    public void setGovtEmployee(boolean govtEmployee) {
        this.govtEmployee = govtEmployee;
    }

    public boolean isChallanPaid() {
        return challanPaid;
    }

    public void setChallanPaid(boolean challanPaid) {
        this.challanPaid = challanPaid;
    }

    public double getChallanAmountPaid() {
        return challanAmountPaid;
    }

    public void setChallanAmountPaid(double challanAmountPaid) {
        this.challanAmountPaid = challanAmountPaid;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public AttributeGroup getAttributeGroup() {
        return attributeGroup;
    }

    public void setAttributeGroup(AttributeGroup attributeGroup) {
        this.attributeGroup = attributeGroup;
    }
}
