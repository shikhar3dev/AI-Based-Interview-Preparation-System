package com.aibased.interviewprep.repository;

import com.aibased.interviewprep.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByUserIdOrderByDateDesc(Long userId);
}
