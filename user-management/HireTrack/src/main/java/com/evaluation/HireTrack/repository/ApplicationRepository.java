package com.evaluation.HireTrack.repository;

import com.evaluation.HireTrack.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ApplicationRepository extends JpaRepository<Application,Integer> {



    @Query("""
           select a from Application a where a.jobSeeker.user.username=?1
""")
    Page<Application> getSeekerApplications(String seekerUsername, Pageable pageable);
}
