package com.jobster.website.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExceptionResponse {
    private String message;
}
