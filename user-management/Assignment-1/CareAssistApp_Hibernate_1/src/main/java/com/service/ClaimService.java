package com.service;

import com.enums.ClaimStatus;
import com.exception.ResourceNotFoundException;
import com.model.Claim;
import com.model.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClaimService {

    private final Session session;
    private PatientService patientService;

    public ClaimService(Session session) {
        this.session = session;
        patientService = new PatientService(session);
    }


    public void insert(Claim claim) {

        Transaction tx = session.beginTransaction();

        session.persist(claim);

        tx.commit();
    }


    public List<Claim> getAllClaims() {

        Transaction tx = session.beginTransaction();

        List<Claim> list = session
                .createQuery("from Claim", Claim.class)
                .list();

        tx.commit();

        return list;
    }


    public Claim getById(int id) {

        Transaction tx = session.beginTransaction();

        Claim claim = session.find(Claim.class, id);

        tx.commit();

        if(claim == null)
            throw new ResourceNotFoundException("Invalid Claim ID");

        return claim;
    }


    public void deleteById(int id) {

        Transaction tx = session.beginTransaction();

        Claim claim = session.find(Claim.class, id);

        if(claim == null) {
            tx.commit();
            throw new ResourceNotFoundException("Invalid Claim ID");
        }

        session.remove(claim);

        tx.commit();
    }
    public void addClaim(Claim claim, String email) {

        Patient patient = patientService.getByEmail(email);

        claim.setPatient(patient);

        claim.setStatus(ClaimStatus.PENDING);

        Transaction tx = session.beginTransaction();

        session.persist(claim);

        tx.commit();
    }
}