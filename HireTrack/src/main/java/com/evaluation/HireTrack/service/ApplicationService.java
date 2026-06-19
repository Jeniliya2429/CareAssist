package com.evaluation.HireTrack.service;

import com.evaluation.HireTrack.dto.ApplicationResponseDto;
import com.evaluation.HireTrack.exception.ResourceNotFoundException;
import com.evaluation.HireTrack.mapper.ApplicationMapper;
import com.evaluation.HireTrack.model.Application;
import com.evaluation.HireTrack.model.Job;
import com.evaluation.HireTrack.model.JobSeeker;
import com.evaluation.HireTrack.repository.ApplicationRepository;
import com.evaluation.HireTrack.repository.JobRepository;
import com.evaluation.HireTrack.repository.JobSeekerRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ApplicationService {

    private final JobSeekerRepository jobSeekerRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;    private final ApplicationMapper applicationMapper;

    public List<ApplicationResponseDto> getSeekerApplications(String seekerUsername, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        List<Application> list = applicationRepository.getSeekerApplications(seekerUsername,pageable).getContent();

        return list.stream()
                .map(applicationMapper::mapEntityToDto).toList();
    }

    public void apply(String username, int jobId){

        JobSeeker jobSeeker = jobSeekerRepository.findByUserUsername(username);

        Job job = jobRepository.findById(jobId)
                             .orElseThrow(() -> new ResourceNotFoundException("Invalid Job Id"));

        Application application = new Application();

        application.setJobSeeker(jobSeeker);
        application.setJob(job);

        applicationRepository.save(application);
    }
}
