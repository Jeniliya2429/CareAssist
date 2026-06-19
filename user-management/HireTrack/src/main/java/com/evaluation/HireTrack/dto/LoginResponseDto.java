package com.evaluation.HireTrack.dto;

public record LoginResponseDto(

        int userId,

        String email,

        String role

) {
}