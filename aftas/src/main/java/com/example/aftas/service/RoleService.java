package com.example.aftas.service;

import com.example.aftas.model.Role;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role) throws ValidationException;
    Optional<Role> findByName(String name) ;
    List<Role> getALlRoles();
    void delete(Long id);

    Role findById(Long id);
}
