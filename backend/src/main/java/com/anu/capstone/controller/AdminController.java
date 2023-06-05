package com.anu.capstone.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anu.capstone.dto.AdminUserJobDto;
import com.anu.capstone.dto.FeedbackDto;
import com.anu.capstone.dto.JobListDto;
import com.anu.capstone.dto.JobpostingDto;
import com.anu.capstone.dto.UpdateJobDto;
import com.anu.capstone.service.AdminService;
import com.anu.capstone.util.AppResponse;

import lombok.AllArgsConstructor;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping(value = "/newjob", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> newJob(@Valid @RequestBody JobpostingDto dto) {
        Integer createNewJob = adminService.createNewJob(dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .msg("new Job created successfully.")
                .bd(createNewJob)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/jobs", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<JobListDto>> findAll() {
        return ResponseEntity.ok().body(adminService.getAllJob());
    }

    @GetMapping(value = "/jobs/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<AppResponse<JobpostingDto>> findEventById(@PathVariable Long id) {

        JobpostingDto dto = adminService.getJob(id);

         AppResponse<JobpostingDto> response = AppResponse.<JobpostingDto>builder()
                                                        .msg("Job Details")
                                                        .bd(dto)
                                                        .build();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/jobs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> deleteJob(@PathVariable Long id) {

        final Integer sts = adminService.deleteJobPost(id);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
            .msg(id+" ID Job Deleted Successfully")
            .bd(sts)
            .build();

        return ResponseEntity.status(200).body(response);
    }

    
    @PutMapping(value = "/updatejob", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> updateNewJob(@Valid @RequestBody UpdateJobDto dto) {

        final Integer sts = adminService.updateJobPost(dto);

        final AppResponse<Integer> response = AppResponse.<Integer>builder()
                                                    .msg("Job Updated Successfully")
                                                    .bd(sts)
                                                    .build();

        return ResponseEntity.ok().body(response);
    

}
//when admin clicks view all userJobs

@GetMapping(value = "/alluserJobs", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AppResponse<List<AdminUserJobDto>>> getAllUserBookings() {
    List<AdminUserJobDto> sts = adminService.getAllUserJobs();
    AppResponse<List<AdminUserJobDto>> response = AppResponse.<List<AdminUserJobDto>>builder()
    .sts("success")
    .msg("All User Bookings")
    .bd(sts)
    .build();
      return ResponseEntity.ok().body(response);
}
//when admin searches useremail and clicks search

@GetMapping(value = "/filterbyemail", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<AppResponse<List<AdminUserJobDto>>> findUserJobsByEmail(@RequestParam String email) {
    List<AdminUserJobDto> sts = adminService.searchUserJobsByEmail(email);
    AppResponse<List<AdminUserJobDto>> response = AppResponse.<List<AdminUserJobDto>>builder()
    .sts("success")
    .msg("All User Bookings")
    .bd(sts)
    .build();
      return ResponseEntity.ok().body(response);

     
}

@PostMapping(value = "/{id}/feedback", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<Integer>> createFeedback(@PathVariable Long id, @RequestBody FeedbackDto dto) {
        Integer sts = adminService.createFeedback(id,dto);
        AppResponse<Integer> response = AppResponse.<Integer>builder()
                .sts("success")
                .msg("feedback submitted.")
                .bd(sts)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/feedback", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppResponse<List<FeedbackDto>>> getAllFeedbacks() {
        List<FeedbackDto> sts = adminService.listAllFeedbacks();
        AppResponse<List<FeedbackDto>> response = AppResponse.<List<FeedbackDto>>builder()
                .sts("success")
                .msg("All Feedbacks")
                .bd(sts)
                .build();
        return ResponseEntity.ok().body(response);
    }

}
