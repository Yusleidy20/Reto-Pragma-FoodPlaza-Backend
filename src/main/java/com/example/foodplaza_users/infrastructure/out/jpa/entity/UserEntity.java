package com.example.foodplaza_users.infrastructure.out.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "nameUser")
    private String nameUser;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "docId",unique = true)
    private Long docId;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;
    @Column(name = "email")
    private String email;
    @Column(name = "passwordUser")
    private String passwordUser;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "idUserRole", referencedColumnName = "idUserRole", nullable = false)
    private RoleEntity roleModel;

    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", nameUser='" + nameUser + '\'' +
                ", lastname='" + lastname + '\'' +
                ", docId=" + docId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", roleModel=" + roleModel +
                '}';
    }
}
