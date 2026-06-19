package com.app.model;


import com.app.enums.ClaimStatus;

public class Claim {

    private int id;

    private double amount;

    private ClaimStatus status;


    public Claim(double amount, ClaimStatus status) {
        this.amount = amount;
        this.status = status;
    }

    public Claim(int id, double amount, ClaimStatus status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "id=" + id +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
