package com.aibased.interviewprep.service;

import org.springframework.stereotype.Service;

@Service
public class PerformanceAnalyticsService {

    public double calculateScore(String answerText) {
        int length = answerText == null ? 0 : answerText.trim().length();
        if (length == 0) {
            return 0.0;
        }
        return Math.min(100.0, 40.0 + (length * 0.2));
    }
}
