package com.evaluation.HireTrack.service;

import com.evaluation.HireTrack.exception.ResourceNotFoundException;
import com.evaluation.HireTrack.model.Employer;
import com.evaluation.HireTrack.repository.EmployerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;


    public Employer getByUsername(String username) {
        return employerRepository.findByUserUsername(username).orElseThrow(()->new ResourceNotFoundException("Invalid Username"));
    }
}
