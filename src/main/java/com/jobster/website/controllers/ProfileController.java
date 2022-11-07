package com.jobster.website.controllers;

import com.jobster.website.dtos.EmployeeProfileDto;
import com.jobster.website.models.Employee;
import com.jobster.website.models.Person;
import com.jobster.website.responses.ExceptionResponse;
import com.jobster.website.services.EmployeeService;
import com.jobster.website.services.PersonService;
import com.jobster.website.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final PersonService personService;
    private final EmployeeService employeeService;

    @Autowired
    public ProfileController(PersonService personService, EmployeeService employeeService) {
        this.personService = personService;
        this.employeeService = employeeService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<EmployeeProfileDto> getEmployeeProfile(@PathVariable String login) {
        Person person = personService.findByLogin(login);
        return ResponseEntity.ok(MapperUtil.personToProfileEmployeeDto(person));
    }

    @PostMapping("/{login}")
    @Transactional
    public ResponseEntity<HttpStatus> createOrUpdateEmployeeProfile(@RequestBody @Valid EmployeeProfileDto profileDto,
                                                                    @PathVariable String login,
                                                                    BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException();
        }

        Person person = personService.findByLogin(login);
        person.setEmail(profileDto.getEmail());
        person.setAbout(profileDto.getAboutMe());

        Employee employee = MapperUtil.employeeProfileDtoToEmployee(profileDto);
        employee.setPerson(person);
        employee = employeeService.save(employee);
        person.setEmployee(employee);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handlePersonValidationException(Exception e) {
        return ResponseEntity.badRequest()
                .body(ExceptionResponse
                        .builder()
                        .message(e.getMessage())
                        .build());
    }
}
