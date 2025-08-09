package com.emp.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //1.Handles custom not-found exception
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ApiError> handleEmployeeNotFound(EmployeeNotFoundException
                                                                      ex){
      ApiError apiError= new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    //2. Handles validations error like @Valid in requestbody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex){
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->fieldErrors.put(error.getField(), error.getDefaultMessage()));
   ApiError apiError= new ApiError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), fieldErrors);
    return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

    }

    //3.Handle all Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGenericException(Exception ex){
       ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
      return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
