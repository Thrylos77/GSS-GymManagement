package com.gss.gss_springboot.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.gss.gss_springboot.models.Suscription;

public interface StatisticService {
    long countActiveCustomers();
    BigDecimal getEstimatedMonthlyRevenue();
    List<Suscription> exportSubscriptions(LocalDate startDate, LocalDate endDate);
}
