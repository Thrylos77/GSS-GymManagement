package com.gss.gss_springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gss.gss_springboot.models.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameAndLastName(String first_name, String last_name);
    // Alternative option for partial search
    // List<Customer> findByFirstNameContainingAndLastNameContaining(String first_name, String last_name);
    long countByActiveSuscriptionTrue();
}
