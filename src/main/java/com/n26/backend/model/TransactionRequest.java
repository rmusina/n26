package com.n26.backend.model;


public class TransactionRequest {
    private long timestamp;

    private double amount;

    public TransactionRequest() {}

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
