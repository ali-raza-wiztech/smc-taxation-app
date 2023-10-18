package com.wiztech.taxation_app.networking.responses;

import com.wiztech.taxation_app.networking.BillLayoutModel;
import com.wiztech.taxation_app.ui.home.ConsumerAccount;
import com.google.gson.annotations.SerializedName;

public class GetCurrentBillData {
    @SerializedName("id")
    int id;
    @SerializedName("toMonth")
    String toMonth;
    @SerializedName("billAmount")
    double billAmount;
    @SerializedName("totalBill")
    double totalBill;
    @SerializedName("billStatuses")
    String billStatuses;
    @SerializedName("generationDate")
    String generationDate;
    @SerializedName("payableAmount")
    double payableAmount;
    @SerializedName("dueDate")
    String dueDate;
    @SerializedName("dueDateIncrement")
    double dueDateIncrement;
    @SerializedName("paymentDate")
    String paymentDate;
    @SerializedName("paidAmount")
    double paidAmount;
    @SerializedName("installmentAmount")
    double installmentAmount;
    @SerializedName("currentBill")
    boolean currentBill;
    @SerializedName("billIssued")
    boolean billIssued;
    @SerializedName("templateName")
    String templateName;

    @SerializedName("billGroup")
    int billGroup;
    @SerializedName("billPaidSlip")
    String billPaidSlip;
    @SerializedName("installment_id")
    int installment_id;
    @SerializedName("billingMonth")
    String billingMonth;

    @SerializedName("billLayout")
    BillLayoutModel billLayout;
    @SerializedName("consumerAccount")
    ConsumerAccount consumerAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public String getBillStatuses() {
        return billStatuses;
    }

    public void setBillStatuses(String billStatuses) {
        this.billStatuses = billStatuses;
    }

    public String getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(String generationDate) {
        this.generationDate = generationDate;
    }

    public double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getDueDateIncrement() {
        return dueDateIncrement;
    }

    public void setDueDateIncrement(double dueDateIncrement) {
        this.dueDateIncrement = dueDateIncrement;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public double getInstallmentAmount() {
        return installmentAmount;
    }

    public void setInstallmentAmount(double installmentAmount) {
        this.installmentAmount = installmentAmount;
    }

    public boolean isCurrentBill() {
        return currentBill;
    }

    public void setCurrentBill(boolean currentBill) {
        this.currentBill = currentBill;
    }

    public boolean isBillIssued() {
        return billIssued;
    }

    public void setBillIssued(boolean billIssued) {
        this.billIssued = billIssued;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public int getBillGroup() {
        return billGroup;
    }

    public void setBillGroup(int billGroup) {
        this.billGroup = billGroup;
    }

    public String getBillPaidSlip() {
        return billPaidSlip;
    }

    public void setBillPaidSlip(String billPaidSlip) {
        this.billPaidSlip = billPaidSlip;
    }

    public int getInstallment_id() {
        return installment_id;
    }

    public void setInstallment_id(int installment_id) {
        this.installment_id = installment_id;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public BillLayoutModel getBillLayout() {
        return billLayout;
    }

    public void setBillLayout(BillLayoutModel billLayout) {
        this.billLayout = billLayout;
    }

    public ConsumerAccount getConsumerAccount() {
        return consumerAccount;
    }

    public void setConsumerAccount(ConsumerAccount consumerAccount) {
        this.consumerAccount = consumerAccount;
    }
}
