package com.gss.gss_springboot.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gss.gss_springboot.models.Suscription;
import com.gss.gss_springboot.services.StatisticService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gss/statistics")
@AllArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;

    // Afficher le nombre total de clients actifs
    @GetMapping("/activeCustomers")
    public ResponseEntity<Long> getActiveCustomers() {
        long count = statisticService.countActiveCustomers();
        return ResponseEntity.ok(count);
    }

    // Afficher le chiffre d'affaires mensuel estimé
    @GetMapping("/monthlyRevenue")
    public ResponseEntity<BigDecimal> getMonthlyRevenue() {
        BigDecimal revenue = statisticService.getEstimatedMonthlyRevenue();
        return ResponseEntity.ok(revenue);
    }

    // Exporter les informations sur les abonnements sur une période donnée
    @GetMapping("/exportSubscriptions")
    public ResponseEntity<List<Suscription>> exportSubscriptions(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        List<Suscription> subscriptions = statisticService.exportSubscriptions(startDate, endDate);
        return ResponseEntity.ok(subscriptions);
    }
}
