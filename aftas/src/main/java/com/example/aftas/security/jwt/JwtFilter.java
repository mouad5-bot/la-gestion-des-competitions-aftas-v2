package com.example.aftas.security.jwt;

import com.example.aftas.service.UserService;
import com.example.aftas.utils.CustomError;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.ErrorManager;
@Configuration
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        final String jwt;
        final String userEmail;

        if (
            StringUtils.isEmpty(authHeader) ||
            !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUserName(jwt);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!StringUtils.isEmpty(userEmail) && authentication == null) {
                UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails, TokenType.ACCESS_TOKEN)) {
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);
                }
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            logger.error("Unauthorized error: {}");
            response.getWriter().write(new CustomError("token", "Token expired").toString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            logger.error("Unauthorized error: {}");
            response.getWriter().write(new CustomError("error", "Error has been occurred").toString());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        }
    }

}
