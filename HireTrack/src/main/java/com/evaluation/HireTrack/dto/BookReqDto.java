package com.evaluation.HireTrack.dto;

import jakarta.validation.constraints.NotBlank;

public record BookReqDto(
        @NotBlank(message = "Title is mandatory")
        String title,
        @NotBlank(message = "Summary is mandatory")
        String summary
) {

}
