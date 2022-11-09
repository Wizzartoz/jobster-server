package com.jobster.website.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
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
    private String git;

    @Column(name = "linkedin")
    @Size(max = 50)
    private String linkedin;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Resume resume;

    @OneToMany(mappedBy = "employeeChatOwner")
    private List<Chat> chats;

    @OneToOne(mappedBy = "employee")
    private Person person;
}
