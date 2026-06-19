package com.evaluation.HireTrack.repository;

import com.evaluation.HireTrack.model.Application;
import com.evaluation.HireTrack.model.JobSeeker;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.net.ContentHandler;

public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {


    JobSeeker findByUserUsername(String username);
}
