package com.aibased.interviewprep.service;

import com.aibased.interviewprep.dto.InterviewRequest;
import com.aibased.interviewprep.dto.InterviewResultResponse;
import com.aibased.interviewprep.model.Interview;
import com.aibased.interviewprep.model.User;
import com.aibased.interviewprep.repository.InterviewRepository;
import com.aibased.interviewprep.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final UserRepository userRepository;
    private final AiIntegrationService aiIntegrationService;
    private final PerformanceAnalyticsService performanceAnalyticsService;

    public InterviewService(InterviewRepository interviewRepository,
                            UserRepository userRepository,
                            AiIntegrationService aiIntegrationService,
                            PerformanceAnalyticsService performanceAnalyticsService) {
        this.interviewRepository = interviewRepository;
        this.userRepository = userRepository;
        this.aiIntegrationService = aiIntegrationService;
        this.performanceAnalyticsService = performanceAnalyticsService;
    }

    public InterviewResultResponse conductInterview(InterviewRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String question = aiIntegrationService.generateQuestion(request.domain(), request.experienceLevel());
        String feedback = aiIntegrationService.generateFeedback(request.answerText());
        double score = performanceAnalyticsService.calculateScore(request.answerText());

        Interview interview = new Interview();
        interview.setUser(user);
        interview.setDomain(request.domain());
        interview.setExperienceLevel(request.experienceLevel());
        interview.setScore(score);
        interview.setFeedback(feedback);

        Interview saved = interviewRepository.save(interview);

        return new InterviewResultResponse(saved.getId(), question, score, feedback, saved.getDate());
    }

    public List<Interview> historyByUser(Long userId) {
        return interviewRepository.findByUserIdOrderByDateDesc(userId);
    }
}
