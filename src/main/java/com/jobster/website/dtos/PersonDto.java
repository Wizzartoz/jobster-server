package com.jobster.website.dtos;

import com.jobster.website.models.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String login;
    private String password;
    private String email;
    private RoleEnum role;
}
