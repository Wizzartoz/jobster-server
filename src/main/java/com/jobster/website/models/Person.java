package com.jobster.website.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "person"
)
public class Person {

    @Column(name = "person_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(name = "login", unique = true)
    @NotEmpty
    @Size(max = 50)
    private String login;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "email", unique = true)
    @NotEmpty
    @Size(max = 50)
    private String email;

    @Column(name = "about")
    @Size(max = 2000)
    private String about;

    @Column(name = "img")
    private String img;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "role")
    private RoleEnum role;

    @OneToOne(mappedBy = "person")
    private Employee employee;

    @OneToOne(mappedBy = "person")
    private Employer employer;

}
