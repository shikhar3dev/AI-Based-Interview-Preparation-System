package com.aibased.interviewprep.controller;

import com.aibased.interviewprep.dto.InterviewRequest;
import com.aibased.interviewprep.dto.InterviewResultResponse;
import com.aibased.interviewprep.model.Interview;
import com.aibased.interviewprep.service.InterviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<InterviewResultResponse> conductInterview(@Valid @RequestBody InterviewRequest request) {
        return ResponseEntity.ok(interviewService.conductInterview(request));
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Interview>> history(@PathVariable Long userId) {
        return ResponseEntity.ok(interviewService.historyByUser(userId));
    }
}
