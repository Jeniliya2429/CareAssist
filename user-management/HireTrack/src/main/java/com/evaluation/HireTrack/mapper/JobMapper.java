package com.evaluation.HireTrack.mapper;

import com.evaluation.HireTrack.dto.JobResponseDto;
import com.evaluation.HireTrack.dto.JobResponsePageDto;
import com.evaluation.HireTrack.model.Job;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobMapper {

    public JobResponseDto mapEntityToDto(Job job){
        String companyName = "Employer Not Found";

        if(job.getEmployer() != null){
            companyName = job.getEmployer().getCompanyName();
        }

        return new JobResponseDto(
                job.getId(),
                job.getTitle(),
                job.getLocation(),
                job.getSalary(),
                companyName
        );

    }
}
