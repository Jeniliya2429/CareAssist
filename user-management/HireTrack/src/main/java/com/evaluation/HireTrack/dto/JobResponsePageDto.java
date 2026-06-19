package com.evaluation.HireTrack.dto;

import java.util.List;

public record JobResponsePageDto(
        long totalRecords,
        long totlaPages,
        List<JobResponseDto>data
) {
}
