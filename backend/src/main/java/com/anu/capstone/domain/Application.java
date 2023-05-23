package com.anu.capstone.domain;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "applicantName is mandatory")
    @NotNull(message = "applicantName should not be null")
    private String applicantName;

    @NotBlank(message = "applicantEmail is mandatory")
    @NotNull(message = "applicantEmail should not be null")
    private String applicantEmail;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="job_posting_id")
    private JobPosting jobPosting;
   
}
