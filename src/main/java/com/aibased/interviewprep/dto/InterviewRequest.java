package com.aibased.interviewprep.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InterviewRequest(
        @NotNull Long userId,
        @NotBlank String domain,
        @NotBlank String experienceLevel,
        @NotBlank String answerText
) {
}
