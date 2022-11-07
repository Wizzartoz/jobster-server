package com.jobster.website.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeProfileDto {
    private Long employeeId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    @Email
    private String email;
    private String git;
    private String linkedin;
    private String aboutMe;
}
