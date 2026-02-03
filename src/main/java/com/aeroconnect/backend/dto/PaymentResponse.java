package com.aeroconnect.backend.dto;

public class PaymentResponse {
    private String orderId;
    private String keyId; // Send keyId to frontend to initialize Razorpay
    private Integer amount;
    private String currency;

    public PaymentResponse(String orderId, String keyId, Integer amount, String currency) {
        this.orderId = orderId;
        this.keyId = keyId;
        this.amount = amount;
        this.currency = currency;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
