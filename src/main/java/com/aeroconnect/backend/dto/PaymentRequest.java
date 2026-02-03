package com.aeroconnect.backend.dto;

public class PaymentRequest {
    private Long flightId;
    private Integer amount; // Amount in paise

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
