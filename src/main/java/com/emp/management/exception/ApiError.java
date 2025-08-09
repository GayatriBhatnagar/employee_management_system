package com.emp.management.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> fieldErrors;

    public ApiError(int httpStatus, String message) {
        this.status = httpStatus;
        this.message= message;

    }

    public ApiError(int value, String message, Map<String, String> fieldErrors) {
        this.status= value;
        this.message= message;
        this.fieldErrors= fieldErrors;
    }
}
