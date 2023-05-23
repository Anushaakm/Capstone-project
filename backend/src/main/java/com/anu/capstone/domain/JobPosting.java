package com.anu.capstone.domain;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "title is mandatory")
    @NotNull(message = "title should not be null")
    private String jobTitle;

    @NotBlank(message = "description is mandatory")
    @NotNull(message = "description should not be null")
    private String jobDescription;

    @NotBlank(message = "location is mandatory")
    @NotNull(message = "location should not be null")
    private String location;

    @NotBlank(message = "industry is mandatory")
    @NotNull(message = "industry should not be null")
    private String industry;

    @NotBlank(message = "industry is mandatory")
    @NotNull(message = "industry should not be null")
    private String qualification;

    @NotBlank(message = "industry is mandatory")
    @NotNull(message = "industry should not be null")
    private String applicationRequirement;

    private LocalDate postedDate;

    @ManyToMany(mappedBy="jobPostings")
    private List<User> users;

    @OneToMany(mappedBy ="jobPosting", cascade = CascadeType.ALL)
    private List<Application> application;

   
}
