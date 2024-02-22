package com.example.aftas.aop.handler;


import com.example.aftas.exception.EmailOrPasswordIncorrectException;
import com.example.aftas.exception.InsufficientTokensException;
import com.example.aftas.exception.ResourceNotFoundException;
import com.example.aftas.exception.UnauthorizedException;
import com.example.aftas.utils.CustomError;
import com.example.aftas.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private Response<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Response<Object> response = new Response<>();
        List<CustomError> errorList = new ArrayList<>();
        response.setMessage("Validation error");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorList.add(new CustomError(fieldName, errorMessage));
        });
        response.setErrors(errorList);
        return response;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    private Response<Object> handleValidationExceptions(ResourceNotFoundException ex) {
        Response<Object> response = new Response<>();
        List<CustomError> errorList = new ArrayList<>();
        response.setMessage("Resource not found");
        errorList.add(CustomError.builder()
                .field(ex.getField())
                .message(ex.getMessage())
                .build());
        response.setErrors(errorList);
        return response;
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(ValidationException.class)
//    private Response<Object> handleValidationExceptions(ValidationException ex) {
//        Response<Object> response = new Response<>();
//        List<CustomError> errorList = new ArrayList<>();
//        response.setMessage("Validation error");
//        errorList.add(CustomError.builder()
//                .field(ex.getCustomError().getField())
//                .message(ex.getCustomError().getMessage())
//                .build());
//        response.setErrors(errorList);
//        return response;
//    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    private ResponseEntity<Response<Object>> handleValidationExceptions(Exception ex) {
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(Response.builder()
                .message("Internal server error")
                .result(ex.getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class, InsufficientTokensException.class})
    public ResponseEntity<Response<Object>> handleUnauthorizedException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Response.builder()
                .message(ex.getMessage())
                .build());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailOrPasswordIncorrectException.class)
    public ResponseEntity<Response<Object>> handleEmailOrPasswordIncorrectException(
            EmailOrPasswordIncorrectException ex){
        CustomError error = new CustomError("credentials", ex.getMessage());
        Response<Object> result = new Response<>();
        result.setErrors(List.of(error));
        result.setMessage(ex.getMessage());
        return ResponseEntity.badRequest()
                .body(result);
    }
}