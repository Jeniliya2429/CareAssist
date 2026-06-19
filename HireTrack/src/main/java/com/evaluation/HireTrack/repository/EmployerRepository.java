package com.evaluation.HireTrack.repository;

import com.evaluation.HireTrack.model.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer,Integer> {
    Optional<Employer> findByUserUsername(String username);}
