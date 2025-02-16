package com.gss.gss_springboot.services.implementations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gss.gss_springboot.models.Suscription;
import com.gss.gss_springboot.repositories.CustomerRepository;
import com.gss.gss_springboot.repositories.SuscriptionRepository;
import com.gss.gss_springboot.services.StatisticService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService{
    private final CustomerRepository customerRepository;
    private final SuscriptionRepository suscriptionRepository;

    @Override
    public long countActiveCustomers() {
        return customerRepository.countByActiveSuscriptionTrue();
    }

    @Override
    public BigDecimal getEstimatedMonthlyRevenue() {
        BigDecimal revenue = suscriptionRepository.sumMonthlyRevenue();
        return revenue != null ? revenue : BigDecimal.ZERO;
    }

    @Override
    public List<Suscription> exportSubscriptions(LocalDate startDate, LocalDate endDate) {
        return suscriptionRepository.findByStartDateBetween(startDate, endDate);
    }

}
