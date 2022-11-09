package com.jobster.website.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "resume"
)
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "job_title")
    @NotEmpty
    @Size(max = 50)
    private String jobTitle;

    @Column(name = "salary_expectations")
    @NotNull
    @Min(0)
    private Integer salaryExpectations;

    @Column(name = "description")
    @Size(max = 2000)
    @NotNull
    private String description;

    @Column(name = "creation_datetime")
    @NotNull
    private Timestamp creationDatetime;

    @Column(name = "pdf_resume")
    @NotEmpty
    private String pdfResume;

    @Column(name = "experience")
    @Min(0)
    private Integer experience;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "employee_id")
    private long employeeId;
}
