package com.example.aftas.security.auth;

import com.example.aftas.dtos.request.SignInRequest;
import com.example.aftas.dtos.request.SignUpRequest;
import com.example.aftas.exception.UnauthorizedException;
import com.example.aftas.model.AppUser;
import com.example.aftas.model.Role;
import com.example.aftas.repository.RoleRepository;
import com.example.aftas.repository.UserRepository;
import com.example.aftas.security.AuthenticationService;
import com.example.aftas.security.jwt.JwtService;
import com.example.aftas.security.jwt.TokenType;
import com.example.aftas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public void signup(SignUpRequest request) throws ValidationException {
        Role role = roleRepository.findByName("ROLE_MEMBER")
                .orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_MEMBER").build()));
        AppUser user = AppUser.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .roles(List.of(role))
                .accountNonLocked(false)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        userService.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var accessToken = jwtService.generateToken(user, TokenType.ACCESS_TOKEN);
        var refreshToken = jwtService.generateToken(user, TokenType.REFRESH_TOKEN);
        return  JwtAuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();    }

    @Override
    public JwtAuthenticationResponse refreshToken(String refreshToken) throws ValidationException {
        if(jwtService.isTokenValid(refreshToken, TokenType.REFRESH_TOKEN)) {
            String username = jwtService.extractUserName(refreshToken);
            var user = userRepository.findByEmail(username).orElseThrow(() -> new ValidationException("User not found"));
            var accessToken = jwtService.generateToken(user, TokenType.ACCESS_TOKEN);
            return JwtAuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
        throw new UnauthorizedException("Refresh token is invalid");    }

    @Override
    public AppUser me() {
        return userService.getCurrentUser();
    }
}
