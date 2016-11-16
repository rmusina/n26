package com.n26.backend.model;


import javax.validation.constraints.Min;

public class TransactionRequest {

    @Min(1)
    public long timestamp;

    @Min(0)
    public double amount;
}
