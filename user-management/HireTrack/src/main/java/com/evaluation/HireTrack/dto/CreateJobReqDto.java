package com.evaluation.HireTrack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateJobReqDto(
        @NotBlank(message = "Name is mandatory")
        String title,
        @NotBlank(message = "Description is mandatory")
        String description,

        @NotBlank(message = "Location is mandatory")
        String location,


        double salary
) {
}
