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
        name = "employee"
)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "first_name")
    @NotEmpty
    @Size(max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Size(max = 50)
    private String lastName;

    @Column(name = "git")
    @Size(max = 50)
    @NotBlank
    private String git;

    @Column(name = "linkedin")
    @Size(max = 50)
    @NotBlank
    private String linkedin;

    @OneToOne(mappedBy = "employee")
    private Resume resume;

    @OneToMany(mappedBy = "employeeChatOwner")
    private List<Chat> chats;

    @OneToOne()
    @JoinColumn(name = "person_id")
    private Person person;
}
