package com.evaluation.HireTrack.controller;

import com.evaluation.HireTrack.dto.CreateJobReqDto;
import com.evaluation.HireTrack.dto.JobResponseDto;
import com.evaluation.HireTrack.dto.JobResponsePageDto;
import com.evaluation.HireTrack.service.JobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class JobController {
    private final JobService jobService;

    @PostMapping("/api/jobs")
    public void addJobs(@Valid @RequestBody CreateJobReqDto dto, Principal principal){
        String username = principal.getName();
        jobService.addJobs(dto,username);
    }

    @GetMapping("/api/jobs")
    public JobResponsePageDto getAllJobs(@RequestParam int page,
                                         @RequestParam  int size){
        return jobService.getAllJobs(page,size);
    }
}
