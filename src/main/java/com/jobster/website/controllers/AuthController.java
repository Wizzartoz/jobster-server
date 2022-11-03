package com.jobster.website.controllers;

import com.jobster.website.dtos.PersonDto;
import com.jobster.website.exeptions.AuthenticationException;
import com.jobster.website.exeptions.PersonValidationException;
import com.jobster.website.models.*;
import com.jobster.website.responses.ExceptionResponse;
import com.jobster.website.responses.JWTResponse;
import com.jobster.website.security.JWTUtil;
import com.jobster.website.security.PersonDetails;
import com.jobster.website.services.AuthServiceImpl;
import com.jobster.website.utils.MapperUtil;
import com.jobster.website.utils.UniqueLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UniqueLoginValidator uniqueLoginValidator;
    private final JWTUtil jwtUtil;
    private final AuthServiceImpl authServiceImpl;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthController(UniqueLoginValidator uniqueLoginValidator, JWTUtil jwtUtil,
                          AuthServiceImpl authServiceImpl,
                          AuthenticationManager authenticationManager) {
        this.uniqueLoginValidator = uniqueLoginValidator;
        this.jwtUtil = jwtUtil;
        this.authServiceImpl = authServiceImpl;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<JWTResponse> registrationPerson(@RequestBody @Valid PersonDto dto, BindingResult bindingResult) {
        Person person = MapperUtil.personDtoToPerson(dto);
        uniqueLoginValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new PersonValidationException("Person doesn't valid");
        }
        authServiceImpl.registration(person);
        String token = jwtUtil.generateToken(person.getLogin());
        return ResponseEntity.ok(JWTResponse.builder()
                .token(token)
                .login(person.getLogin())
                .role(person.getRole().name())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody @Valid PersonDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new PersonValidationException("Person doesn't valid");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword());
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Login or password doesn't valid");
        }
        String token = jwtUtil.generateToken(dto.getLogin());
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        return ResponseEntity.ok(JWTResponse.builder()
                .token(token)
                .login(personDetails.getUsername())
                .role(personDetails.getAuthorities().toArray()[0].toString())
                .build());
    }

    @ExceptionHandler({AuthenticationException.class, PersonValidationException.class})
    public ResponseEntity<ExceptionResponse> handlePersonValidationException(Exception e) {
        return ResponseEntity.badRequest()
                .body(ExceptionResponse
                        .builder()
                        .message(e.getMessage())
                        .build());
    }
}
