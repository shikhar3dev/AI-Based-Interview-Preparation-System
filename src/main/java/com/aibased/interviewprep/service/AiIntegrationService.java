package com.aibased.interviewprep.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class AiIntegrationService {

    private final WebClient webClient;

    @Value("${ai.api.url:}")
    private String aiApiUrl;

    public AiIntegrationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String generateQuestion(String domain, String experienceLevel) {
        if (aiApiUrl == null || aiApiUrl.isBlank()) {
            return "Tell me about a challenging " + domain + " problem you solved as a " + experienceLevel + " candidate.";
        }

        return webClient.post()
                .uri(aiApiUrl + "/generate-question")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("domain", domain, "experienceLevel", experienceLevel))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorReturn("Explain your approach to solving a real-world " + domain + " issue.")
                .blockOptional()
                .orElse("Explain your approach to solving a real-world " + domain + " issue.");
    }

    public String generateFeedback(String answerText) {
        if (answerText.length() < 100) {
            return "Answer is brief. Add more structure with context, action, and measurable result.";
        }
        return "Good depth. Improve by adding quantified outcomes and emphasizing decision-making rationale.";
    }
}
