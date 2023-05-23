package com.anu.capstone.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.anu.capstone.domain.Application;

public interface JobApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobPostingId(Long jobPostId);   

}
