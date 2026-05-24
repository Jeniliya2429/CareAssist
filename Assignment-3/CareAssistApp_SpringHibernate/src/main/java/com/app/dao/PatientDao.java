package com.app.dao;

import com.app.model.Patient;

public interface PatientDao {

    Patient getByEmail(String email);
}