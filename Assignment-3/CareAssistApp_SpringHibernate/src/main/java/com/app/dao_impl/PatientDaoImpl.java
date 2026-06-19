package com.app.dao_impl;

import com.app.dao.PatientDao;
import com.app.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class PatientDaoImpl implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Patient getByEmail(String email) {

        String sql = "select p from Patient p where p.user.email=?1";

        TypedQuery<Patient> query = entityManager.createQuery(sql, Patient.class);

        query.setParameter(1, email);

        return query.getSingleResult();
    }
}