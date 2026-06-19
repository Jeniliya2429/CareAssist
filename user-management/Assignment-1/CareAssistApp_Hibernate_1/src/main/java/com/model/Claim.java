package com.model;

import com.enums.ClaimStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int claimId;

    private double amount;

    @Enumerated(EnumType.STRING)
    private ClaimStatus status;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToOne
    private Patient patient;

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimId=" + claimId +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", patient=" + patient +
                '}';
    }
}