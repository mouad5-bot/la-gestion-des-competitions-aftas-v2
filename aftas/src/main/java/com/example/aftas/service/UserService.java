package com.example.aftas.service;

import com.example.aftas.dtos.RoleDto;
import com.example.aftas.model.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    AppUser save (AppUser user);

    List<AppUser> findAll();

    Optional<AppUser> findById(Long id);

    Optional<AppUser> findByEmail(String email);

    void revokeRole(Long id, List<RoleDto> roles) throws ValidationException;

    AppUser assigneRole(Long id, List<RoleDto> roles) throws ValidationException;

    List<String> getAuthorities();

    UserDetailsService userDetailsService();

    AppUser findByUsername(String username);

    List<String> getMyAuthorities();

    public AppUser getCurrentUser();

    void delete(Long id);

    void softDelete(Long id);

    void forceDelete(Long id);
}
