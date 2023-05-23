package com.anu.capstone.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    @NotNull(message = "Email should not be null")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid Email")
    private String email;

    @NotNull(message = "Password should not be null")
    @NotBlank(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Role should not be null")
    @NotBlank(message = "Role is mandatory")
    private String role;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_application",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "job_post_id")
    )
    private List<JobPosting> jobPostings;

}