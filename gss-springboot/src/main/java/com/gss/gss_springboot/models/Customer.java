package com.gss.gss_springboot.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cust")
    private Long idCust;

    @Column(length = 120, nullable = false)
    private String firstName;

    @Column(length = 60, nullable = false)
    private String lastName;

    /* Utilisatin de LocalDate : Plus moderne(Hibernate/JPA), 
    ne contient que la date(pas l'heure), plus sûr car immutable*/
    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(length = 12, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean activeSuscription = false;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Suscription> suscriptions = new HashSet<>();
}
