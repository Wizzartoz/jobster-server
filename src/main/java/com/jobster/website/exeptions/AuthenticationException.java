package com.jobster.website.exeptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String msg) {
        super(msg);
    }
}
