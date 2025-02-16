package com.gss.gss_springboot.models;

import java.math.BigDecimal;
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
@Table(name = "packs")
@Getter
@Setter
@NoArgsConstructor
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pack")
    private Long idPack;

    @Column(length = 120, nullable = false, unique = true)
    private String offerName;

    @Column(length = 3, nullable = false)
    private Long durationMonths;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal monthlyPrice; // BigDecimal : très précisz, gère l'arrondi donc recommandé pour le monétaire

    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
    private Set<Suscription> suscriptions = new HashSet<>();
}
