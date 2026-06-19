package com.app.dao;

import com.app.enums.ClaimStatus;
import com.app.model.Claim;

import java.util.List;

public interface ClaimDao {

    void save(Claim claim, String email);

    List<Claim> findAll(String email);

    Claim getById(int id, String email);

    void update(Claim claim);

    void deleteById(int id, String email);

    List<Claim> findAllClaims();

    void updateStatus(int claimId, ClaimStatus status);
}