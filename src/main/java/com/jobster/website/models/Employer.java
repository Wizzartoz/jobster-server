package com.jobster.website.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "employer"
)
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employer_id")
    private Long employerId;

    @Column(name = "company_name", unique = true)
    @NotEmpty
    @Size(max = 50)
    private String companyName;

    @Column(name = "employees_number")
    private Integer employeesNumber;

    @OneToMany(mappedBy = "employer")
    private List<Vacancy> vacancies;

    @OneToMany(mappedBy = "employerChatOwner")
    private List<Chat> chats;

    @OneToOne()
    @JoinColumn(name = "person_id")
    private Person person;
}
