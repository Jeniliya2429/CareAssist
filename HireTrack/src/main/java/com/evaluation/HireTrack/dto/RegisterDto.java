package com.evaluation.HireTrack.dto;

import com.evaluation.HireTrack.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDto(

        String name,

        String companyName,

        String resumeSummary,

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 8)
        String password,

        Role role

) {
}