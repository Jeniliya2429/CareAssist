package com.app.dao_impl;

import com.app.dao.ClaimDao;
import com.app.enums.ClaimStatus;
import com.app.exception.InvalidOwnershipException;
import com.app.exception.ResourceNotFoundException;
import com.app.model.Claim;
import com.app.model.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class ClaimDaoImpl implements ClaimDao {

    @PersistenceContext
    private EntityManager entityManager;

    private PatientDaoImpl patientDao;

    @Autowired
    public void setPatientDao(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public void save(Claim claim, String email) {

        Patient patient = patientDao.getByEmail(email);

        claim.setPatient(patient);

        claim.setStatus(ClaimStatus.PENDING);

        entityManager.persist(claim);
    }

    @Override
    public List<Claim> findAll(String email) {
        TypedQuery<Claim> query = entityManager.createQuery("select c from Claim c where c.patient.user.email=:email", Claim.class);

        query.setParameter("email", email);
        return query.getResultList();
    }

    @Override
    public Claim getById(int id, String email) {

        Claim claim = entityManager.find(Claim.class, id);

        if(claim == null) throw new ResourceNotFoundException("Invalid Claim Id");

        if(!(claim.getPatient().getUser().getEmail().equals(email))){

            throw new InvalidOwnershipException("You do not own this claim");
        }

        return claim;
    }

    @Override
    public void update(Claim claim) {

        entityManager.merge(claim);
    }

    @Override
    public void deleteById(int id, String email) {

        Claim claim = getById(id, email);

        entityManager.remove(claim);
    }
    @Override
    public List<Claim> findAllClaims() {

        TypedQuery<Claim> query = entityManager.createQuery("select c from Claim c", Claim.class);

        return query.getResultList();
    }
    @Override
    public void updateStatus(int claimId, ClaimStatus status) {

        Claim claim = entityManager.find(Claim.class, claimId);

        if(claim == null) throw new ResourceNotFoundException("Invalid Claim Id");

        claim.setStatus(status);

        entityManager.merge(claim);
    }

}