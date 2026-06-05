package com.evaluation.HireTrack.service;

import com.evaluation.HireTrack.dto.RegisterDto;
import com.evaluation.HireTrack.enums.Role;
import com.evaluation.HireTrack.model.Employer;
import com.evaluation.HireTrack.model.JobSeeker;
import com.evaluation.HireTrack.model.User;
import com.evaluation.HireTrack.repository.EmployerRepository;
import com.evaluation.HireTrack.repository.JobSeekerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;

    private final JobSeekerRepository jobSeekerRepository;

    private final EmployerRepository employerRepository;

    private final PasswordEncoder passwordEncoder;

    public void register(RegisterDto dto){

        User user = new User();

        user.setUsername(dto.username());
        String encodedPassword = passwordEncoder.encode(dto.password());
        user.setPassword(encodedPassword);
        user.setRole(dto.role());

        userService.save(user);

        if(dto.role() == Role.SEEKER){

            JobSeeker seeker = new JobSeeker();

            seeker.setName(dto.name());
            seeker.setResumeSummary(dto.resumeSummary());
            seeker.setUser(user);

            jobSeekerRepository.save(seeker);
        }

        if(dto.role() == Role.EMPLOYER){
            Employer employer = new Employer();

            employer.setCompanyName(dto.companyName());
            employer.setUser(user);

            employerRepository.save(employer);
        }
    }
}
