package com.gss.gss_springboot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "lastname_user", length = 30, nullable = false)
    private String lastname;

    @Column(name = "firstname_user", length = 120, nullable = false)
    private String firstname;

    @Column(name = "username_user", length = 60, nullable = false, unique = true)
    private String username;

    @Column(name = "password_user", length = 64, nullable = false)
    private String password;

    @Column(name = "email_user", length = 60, nullable = false)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}
