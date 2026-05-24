package com.service;

import com.model.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PatientService {

    private final Session session;

    public PatientService(Session session) {
        this.session = session;
    }

    public Patient getByEmail(String email) {

        Transaction tx = session.beginTransaction();

        Patient patient = session.createQuery("select p from Patient p where p.user.email=:email", Patient.class)
                .setParameter("email", email)
                .getSingleResult();

        tx.commit();

        return patient;
    }
}