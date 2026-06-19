package com.evaluation.HireTrack.controller;

import com.evaluation.HireTrack.dto.ApplicationResponseDto;
import com.evaluation.HireTrack.service.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;


    @GetMapping("/api/my-applications")
    public List<ApplicationResponseDto> getSeekerApplications (Principal principal, @RequestParam(defaultValue = "0",required = false) int page,
                                                               @RequestParam (defaultValue = "10",required = false) int size){
        String seekerUsername = principal.getName();
        return applicationService.getSeekerApplications(seekerUsername,page,size);
    }

    @PostMapping("/api/applications/{jobId}")
    public void apply(
            @RequestParam int jobId,
            Principal principal) {

        String username = principal.getName();

        applicationService.apply(username, jobId);
    }
}
