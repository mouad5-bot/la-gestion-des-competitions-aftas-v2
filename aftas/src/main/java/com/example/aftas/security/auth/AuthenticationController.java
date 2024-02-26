package com.example.aftas.security.auth;

import com.example.aftas.dtos.mapper.UserDtoMapper;
import com.example.aftas.dtos.request.SignInRequest;
import com.example.aftas.dtos.request.SignUpRequest;
import com.example.aftas.dtos.response.UserResponseDto;
import com.example.aftas.exception.UnauthorizedException;
import com.example.aftas.model.AppUser;
import com.example.aftas.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.aftas.utils.ResponseApi;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;
@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody @Valid SignInRequest credential) {
        JwtAuthenticationResponse result = authenticationService.signin(credential);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseApi<String>> signup(@RequestBody @Valid SignUpRequest register) throws ValidationException {
        authenticationService.signup(register);
        return ResponseEntity.ok(ResponseApi.<String>builder().message("Thank you for register, wait the manager to approve you!").build());
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me() {
        AppUser result = authenticationService.me();
        return ResponseEntity.ok(UserDtoMapper.toDto(result));
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refreshToken(HttpServletRequest request) throws ValidationException {
        String authorization = request.getHeader("Authorization");
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            throw new UnauthorizedException("Refresh token is missing");
        }
        String token = authorization.substring(7);
        JwtAuthenticationResponse result = authenticationService.refreshToken(token);
        return ResponseEntity.ok(result);
    }
}
