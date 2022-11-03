package com.jobster.website.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "vacancy"
)
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacancy_id")
    private Long vacancyId;

    @Column(name = "title")
    @NotEmpty
    @Size(max = 50)
    private String title;

    @Column(name = "salary")
    @NotNull
    @Min(0)
    private Integer salary;

    @Column(name = "description")
    @Size(max = 2000)
    @NotEmpty
    private String description;

    @Column(name = "creation_datetime")
    @NotNull
    private Timestamp creationDatetime;

    @Column(name = "experience")
    @Min(0)
    private Integer experience;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToOne(mappedBy = "vacancy")
    private Chat chat;
}
