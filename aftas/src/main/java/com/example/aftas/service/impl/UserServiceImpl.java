package com.example.aftas.service.impl;

import com.example.aftas.dtos.RoleDto;
import com.example.aftas.exception.EmailOrPasswordIncorrectException;
import com.example.aftas.security.SecurityUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import com.example.aftas.model.AppUser;
import com.example.aftas.model.Role;
import com.example.aftas.repository.RoleRepository;
import com.example.aftas.repository.UserRepository;
import com.example.aftas.service.RoleService;
import com.example.aftas.service.UserService;
import com.example.aftas.utils.CustomError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    @Override
    public AppUser save(AppUser user) {
        findByEmail(user.getEmail()).ifPresent(u -> {
            throw new EmailOrPasswordIncorrectException("Email is already exist !");
        });
        return userRepository.save(user);    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();    }

    @Override
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);    }

    @Override
    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);    }

    @Override
    public void revokeRole(Long id, List<RoleDto> roles) throws ValidationException {
        Optional<AppUser> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            AppUser user = userOptional.get();
            List<Role> roleList = new ArrayList<>();
            roles.forEach(roleDto -> roleService.findByName(roleDto.getName()).ifPresent(roleList::add));

            if (new HashSet<>(user.getRoles()).containsAll(roleList)) {
                user.getRoles().removeAll(roleList);
                userRepository.save(user);
            }
//             else {
//                throw new ValidationException(CustomError.builder()
//                        .field("roles")
//                        .message("User does not have all specified roles.")
//                        .build());
//            }
//        }
//        else {
//            throw new ValidationException(CustomError.builder()
//                    .field("user id")
//                    .message("User does not exist")
//                    .build());
        }
    }

    @Override
    public AppUser assigneRole(Long id, List<RoleDto> roles) throws ValidationException {
        Optional<AppUser> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            AppUser user = userOptional.get();
            List<Role> roleList = new ArrayList<>();
            final int[] someExist = {0};
            roles.forEach(roleDto ->
                    roleService.findByName(roleDto.getName())
                            .ifPresentOrElse(
                                    role -> {
                                        if (user.getRoles().contains(role))
                                            someExist[0] = 1;
                                        roleList.add(role);
                                    },
                                    () -> roleList.add(Role.builder().name(roleDto.getName()).build())));
            if(someExist[0] == 1)
                throw new ValidationException(String.valueOf(CustomError.builder().field("roles").message("User already has some of specified roles.").build()));
            roleRepository.saveAll(roleList);
            user.getRoles().addAll(roleList);
            return userRepository.save(user);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public List<String> getAuthorities() {
        return roleService.getALlRoles().stream().map(Role::getName).toList();
    }

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("this user is not found !"));    }

    @Override
    public AppUser findByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("this user is not found !"));    }

    @Override
    public List<String> getMyAuthorities() {
        return getCurrentUser()
                .getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                .toList();
    }

    @Override
    public AppUser getCurrentUser() {
        String currentUserLogin = SecurityUtils.getUserEmail();
        if(currentUserLogin == null)
            throw new BadCredentialsException("this user is not found !");
        return this.findByUsername(currentUserLogin);
            }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void softDelete(Long id) {
        userRepository.softDelete(id);
    }

    @Override
    public void forceDelete(Long id) {
        userRepository.forceDelete(id);
    }
}
