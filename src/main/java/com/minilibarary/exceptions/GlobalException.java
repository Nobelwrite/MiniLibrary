package com.minilibarary.exceptions;


import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleException(MethodArgumentNotValidException exception) {
        Map<String, String> errorDetails = new HashMap<>();
        BindingResult bindingResult = exception.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrorList) {
            errorDetails.put(error.getField(), error.getDefaultMessage());

        }
        return errorDetails;

    }
}

