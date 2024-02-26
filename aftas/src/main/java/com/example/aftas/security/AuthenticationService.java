package com.example.aftas.security;

import com.example.aftas.dtos.request.SignInRequest;
import com.example.aftas.dtos.request.SignUpRequest;
import com.example.aftas.model.AppUser;
import com.example.aftas.security.auth.JwtAuthenticationResponse;

import javax.validation.ValidationException;

public interface AuthenticationService {
    void signup(SignUpRequest request) throws ValidationException;

    JwtAuthenticationResponse signin(SignInRequest request);

    JwtAuthenticationResponse refreshToken(String refreshToken) throws ValidationException;

    AppUser me();
}
