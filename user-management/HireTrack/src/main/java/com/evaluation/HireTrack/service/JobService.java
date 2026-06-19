package com.evaluation.HireTrack.service;

import com.evaluation.HireTrack.dto.CreateJobReqDto;
import com.evaluation.HireTrack.dto.JobResponseDto;
import com.evaluation.HireTrack.dto.JobResponsePageDto;
import com.evaluation.HireTrack.mapper.JobMapper;
import com.evaluation.HireTrack.model.Employer;
import com.evaluation.HireTrack.model.Job;
import com.evaluation.HireTrack.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final EmployerService employerService;

    public void addJobs(CreateJobReqDto dto,String username){
        Employer employer = employerService.getByUsername(username);
        Job job = new Job();
        job.setTitle(dto.title());
        job.setDescription(dto.description());
        job.setLocation(dto.location());
        job.setSalary(dto.salary());
        job.setEmployer(employer);

        jobRepository.save(job);
    }

    public JobResponsePageDto getAllJobs(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        Page<Job> pages = jobRepository.findAll(pageable);
        long totalElements = pages.getTotalElements();
        long totalPages = pages.getTotalPages();
        List<Job> list = pages.getContent();


        List<JobResponseDto> data=list.stream().map(jobMapper::mapEntityToDto).toList();
        return new JobResponsePageDto(
                totalElements,
                totalPages,
                data
        );



    }
}
