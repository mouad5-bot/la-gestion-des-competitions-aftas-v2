package com.example.aftas.dtos.mapper;

import com.example.aftas.dtos.request.UserRequestDto;
import com.example.aftas.dtos.response.UserResponseDto;
import com.example.aftas.model.AppUser;
import com.example.aftas.model.Role;

import java.util.ArrayList;
import java.util.List;

public class UserDtoMapper {

    private UserDtoMapper() {
    }

    public static AppUser toEntity(UserRequestDto userDto) {
        List<Role> roles = new ArrayList<>();
        if(userDto.getAuthorities() != null){
            for (String role : userDto.getAuthorities()) {
                roles.add(Role.builder().name(role).build());
            }
        }
        return AppUser.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .roles(roles)
                .build();
    }

    public static UserResponseDto toDto(AppUser user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .verifiedAt(user.getVerifiedAt())
                .authorities(user.getRoles().stream().map(Role::getName).toList())
                .build();
    }
}
