package com.anu.capstone.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anu.capstone.dto.JobListDto;
import com.anu.capstone.dto.UserJobPostDto;
import com.anu.capstone.service.UserService;
import com.anu.capstone.util.AppResponse;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/jobseeker")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/{userId}/job/{jobPostId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> newJobPost(@Valid @PathVariable Long userId, @PathVariable Long jobPostId) {
        Integer applyJobPost = userService.applyJob(userId, jobPostId);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .msg("new job applied successfully.")
                .bd(applyJobPost)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserJobPostDto>> findAll(@PathVariable Long userId) {

        return ResponseEntity.ok().body(userService.getAllJobs(userId));
    }

    @GetMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobListDto>> findJobByIdLoction(@RequestParam String location) {

        return ResponseEntity.ok().body(userService.getJobsByLocation(location));
    }

    //@GetMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    //public ResponseEntity<List<JobListDto>> findJobByIdIndustry(@RequestParam String industry) {

     //   return ResponseEntity.ok().body(userService.getJobsByIndusry(industry));
    //}
    @GetMapping(value = "/{userId}/job/{jobPostId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<UserJobPostDto>> findEventById(@PathVariable Long userId,
            @PathVariable Long jobPostId) {

        UserJobPostDto dto = userService.getJob(userId, jobPostId);

        AppResponse<UserJobPostDto> response = AppResponse.<UserJobPostDto>builder()
                .msg("Job Details")
                .bd(dto)
                .build();
        return ResponseEntity.ok().body(response);
    }

}
