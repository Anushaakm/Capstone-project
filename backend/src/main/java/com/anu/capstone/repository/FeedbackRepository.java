package com.anu.capstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anu.capstone.domain.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    
}