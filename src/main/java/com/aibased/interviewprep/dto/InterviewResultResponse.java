package com.aibased.interviewprep.dto;

import java.time.LocalDateTime;

public record InterviewResultResponse(
        Long interviewId,
        String generatedQuestion,
        Double score,
        String feedback,
        LocalDateTime date
) {
}
