package com.example.aftas.handler.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;
    private Map<String, String> errors;

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder().status("success").message(message).data(data).build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder().status("failed").message(message).build();
    }

    public static <T> ApiResponse<T> validationError(BindingResult bindingResult) {
        ApiResponse<T> response = ApiResponse.error("validation failed");
        response.errors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            response.errors.put(error.getField(), error.getDefaultMessage());
        }

        return response;
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return ApiResponse.<T>builder().status("notFound").message(message).build();
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return ApiResponse.<T>builder().status("ok").message(message).data(data).build();
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return ApiResponse.<T>builder().status("badRequest").message(message).build();
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return ApiResponse.<T>builder().status("created").message(message).data(data).build();
    }
}
