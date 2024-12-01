package com.example.foodplaza.infrastructure.out.jpa.feignclients;


import com.example.foodplaza.domain.model.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDto {
    private Long userId;
    private String nameUser;
    private String lastname;
    private Long docId;
    private String phoneNumber;
    private LocalDate birthDate;
    private String email;
    private String passwordUser;
    private RoleDto userRole;
}