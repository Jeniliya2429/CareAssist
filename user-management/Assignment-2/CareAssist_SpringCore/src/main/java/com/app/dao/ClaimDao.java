package com.app.dao;

import com.app.exception.ResourceNotFoundException;
import com.app.model.Claim;

import java.util.List;

public interface ClaimDao {

    void insert(Claim claim);

    List<Claim> getAll();

    Claim getById(int id);

    void deleteById(int id) throws ResourceNotFoundException;

    void update(Claim claim);
}
