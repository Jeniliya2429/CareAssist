package com.evaluation.HireTrack.repository;

import com.evaluation.HireTrack.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Integer> {
}
