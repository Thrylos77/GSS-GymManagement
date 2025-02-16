package com.gss.gss_springboot.models;

import java.time.LocalDate;

import org.hibernate.annotations.Comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suscriptions")
@Getter
@Setter
@NoArgsConstructor
public class Suscription {    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_susc")
        private Long idSusc;
    
        @Column(nullable = false)
        private LocalDate startDate;
    
        @ManyToOne
        @JoinColumn(name = "id_cust", nullable = false)
        @Comment("Foreign key to customers table")
        private Customer customer;
        
        @ManyToOne
        @JoinColumn(name = "id_pack", nullable = false)
        @Comment("Foreign key to packs table")
        private Pack pack;
    
}