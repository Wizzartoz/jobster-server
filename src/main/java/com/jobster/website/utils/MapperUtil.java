package com.jobster.website.utils;

import com.jobster.website.dtos.EmployeeProfileDto;
import com.jobster.website.dtos.PersonDto;
import com.jobster.website.models.Employee;
import com.jobster.website.models.Person;
import lombok.experimental.UtilityClass;
@UtilityClass
public class MapperUtil {

    public EmployeeProfileDto personToProfileEmployeeDto(Person person) {
        EmployeeProfileDto dto = new EmployeeProfileDto();
        Employee employee = person.getEmployee();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setName(employee.getFirstName());
        dto.setSurname(employee.getLastName());
        dto.setGit(employee.getGit());
        dto.setLinkedin(employee.getLinkedin());
        dto.setEmail(person.getEmail());
        dto.setAboutMe(person.getAbout());
        return dto;
    }

    public Employee employeeProfileDtoToEmployee(EmployeeProfileDto dto) {
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setGit(dto.getGit());
        employee.setLinkedin(dto.getLinkedin());
        employee.setFirstName(dto.getName());
        employee.setLastName(dto.getSurname());
        return employee;
    }

    public static Person personDtoToPerson(PersonDto dto) {
        Person person = new Person();
        person.setLogin(dto.getLogin());
        person.setPassword(dto.getPassword());
        person.setEmail(dto.getEmail());
        person.setRole(dto.getRole());
        return person;
    }
}
