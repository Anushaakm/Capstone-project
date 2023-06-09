package com.anu.capstone.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.anu.capstone.domain.JobPosting;
import com.anu.capstone.domain.User;
import com.anu.capstone.dto.JobListDto;
import com.anu.capstone.dto.UserJobPostDto;
import com.anu.capstone.exception.DuplicateJobException;

import com.anu.capstone.exception.InvalidRoleException;
import com.anu.capstone.exception.JobNotFoundException;
import com.anu.capstone.exception.UserNotFoundException;
import com.anu.capstone.repository.JobPostRepository;
import com.anu.capstone.repository.UserRepository;
import com.anu.capstone.util.DynamicMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JobPostRepository adminRepository;
    private final DynamicMapper dynamicMapper;

    @Override
    public Integer applyJob(Long userId, Long jobPostId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't apply Job");

        JobPosting jobPosting = adminRepository.findById(jobPostId)
                .orElseThrow(() -> new JobNotFoundException("Job not Found for " + jobPostId + " id"));

        if (user.getJobPostings().contains(jobPosting))
            throw new DuplicateJobException("Job already applied...");

        user.getJobPostings().add(jobPosting);
        userRepository.save(user);
        return 1;
    }

    @Override
    public List<UserJobPostDto> getAllJobs(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("No jobs for Admin");

        List<UserJobPostDto> collect = user.getJobPostings()
                .stream()
                .map(jobPosting -> dynamicMapper.convertor(jobPosting, new UserJobPostDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new JobNotFoundException("No Job found.");

        return collect;
    }

    @Override
    public List<JobListDto> getJobsByLocation(String location) {
        List<JobListDto> collect = adminRepository.findAllByLocation(location)
                .stream()
                .map(jobPosting -> dynamicMapper.convertor(jobPosting, new JobListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new JobNotFoundException("No job found for location");

        return collect;
    }

    @Override
    public List<JobListDto> getJobsByIndusry(String industry) {
        List<JobListDto> collect = adminRepository.findAllByIndustry(industry)
                .stream()
                .map(jobPosting -> dynamicMapper.convertor(jobPosting, new JobListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new JobNotFoundException("No job found for industry");

        return collect;
    }

    @Override
    public List<JobListDto> getJobsByJobTitle(String jobTitle) {
        List<JobListDto> collect = adminRepository.findAllByJobTitle(jobTitle)
                .stream()
                .map(jobPosting -> dynamicMapper.convertor(jobPosting, new JobListDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new JobNotFoundException("No job found for keyword");

        return collect;
    }

    @Override
    public UserJobPostDto getJob(Long userId, Long jobPostId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't apply job");
            
        //Retrieve the JobPosting object with the given jobPostId from the user's jobPostings collection
        return user.getJobPostings()
                .stream()
                .filter(jobPosting -> jobPosting.getId().equals(jobPostId))
                .findFirst().map(jobPosting -> dynamicMapper.convertor(jobPosting, new UserJobPostDto()))
                .orElseThrow(() -> new JobNotFoundException("Job not found"));
    }

    @Override
    public UserJobPostDto getSavedJob(Long userId, Long jobPostId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Admin can't save job");

        return user.getSavedJobPostings()
                .stream()
                .filter(jobPosting -> jobPosting.getId().equals(jobPostId))
                .findFirst().map(jobPosting -> dynamicMapper.convertor(jobPosting, new UserJobPostDto()))
                .orElseThrow(() -> new JobNotFoundException("Job not found"));
    }

    @Override
    public Integer SaveJobPosting(Long userId, Long jobPostId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for ID: " + userId));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("Only job seekers can save job postings.");

        JobPosting jobPosting = adminRepository.findById(jobPostId)
                .orElseThrow(() -> new JobNotFoundException("Job not found for ID: " + jobPostId));

        if (user.getSavedJobPostings().contains(jobPosting))
            throw new DuplicateJobException("Job posting is already saved.");

        user.getSavedJobPostings().add(jobPosting);
        userRepository.save(user);

        return 1;
    }

    @Override
    public List<UserJobPostDto> getSavedJob(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No User found for " + userId + " ID"));

        if (user.getRole().equals("admin"))
            throw new InvalidRoleException("No jobs for Admin");

        List<UserJobPostDto> collect = user.getSavedJobPostings()
                .stream()
                .map(jobPosting -> dynamicMapper.convertor(jobPosting, new UserJobPostDto()))
                .collect(Collectors.toList());
        if (collect.isEmpty())
            throw new JobNotFoundException("No Job found.");

        return collect;
    }

    // @Override
    // public List<FeedbackDto> getFeedback(Long jobPostId) {
    // JobPosting jobPosting = adminRepository.findById(jobPostId)
    // .orElseThrow(() -> new UserNotFoundException("No User found for " + jobPostId
    // + " ID"));
    // List<Feedback> notifications =
    // feedbackRepository.findByJobPosting(jobPosting);
    // List<FeedbackDto> notificationDtos = new ArrayList<>();

    // for (Feedback notification : notifications) {
    // FeedbackDto notificationDto = new FeedbackDto();
    // notificationDto.setId(notification.getId());
    // notificationDto.setComment(notification.getComment());
    // notificationDto.setLocation(notification.getLocation());
    // notificationDto.setInterviewDate(notification.getInterviewDate());
    // notificationDtos.add(notificationDto);
    // }
    // return notificationDtos;
    // }
}
