package com.example.aftas.security;

import com.example.aftas.model.AppUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils(){}
    public static String getUserEmail(){
        AppUser authentication =(AppUser) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (authentication == null)
            return null;
        return authentication.getEmail();
    }
}
