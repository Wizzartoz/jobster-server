package com.jobster.website.exeptions;

public class PersonValidationException  extends RuntimeException{
    public PersonValidationException(String msg) {
        super(msg);
    }
}
