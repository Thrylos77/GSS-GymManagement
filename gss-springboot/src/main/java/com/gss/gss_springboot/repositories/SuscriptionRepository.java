package com.gss.gss_springboot.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gss.gss_springboot.models.Customer;
import com.gss.gss_springboot.models.Suscription;

@Repository
public interface SuscriptionRepository extends JpaRepository<Suscription, Long>{
    // Optionnel retrouver la suscription active d'un client si besoin
    List<Suscription> findByCustomerAndCustomerActiveSuscriptionTrue(Customer customer);
      @Query("SELECT SUM(s.pack.monthlyPrice) FROM Suscription s WHERE s.customer.activeSuscription = true")
    BigDecimal sumMonthlyRevenue();

    List<Suscription> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
