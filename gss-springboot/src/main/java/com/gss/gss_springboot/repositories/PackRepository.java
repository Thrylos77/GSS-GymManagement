package com.gss.gss_springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gss.gss_springboot.models.Pack;

@Repository
public interface PackRepository extends JpaRepository<Pack, Long>{

}
