package com.evaluation.HireTrack.dto;

import java.time.Instant;

public record ApplicationResponseDto(
        int id ,
        Instant appliedAt,
        String jobTitle,
        String companyName
) {
}
