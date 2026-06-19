package com.evaluation.HireTrack.mapper;

import com.evaluation.HireTrack.dto.ApplicationResponseDto;
import com.evaluation.HireTrack.model.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public ApplicationResponseDto mapEntityToDto(Application application){
        return new ApplicationResponseDto(
                application.getId(),
                application.getAppliedAt(),
                application.getJob().getTitle(),
                application.getJob().getEmployer().getCompanyName()
        );
    }
}
