package com.gss.gss_springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gss.gss_springboot.models.Customer;
import com.gss.gss_springboot.models.Suscription;
import com.gss.gss_springboot.services.SuscriptionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/gss/susc")
@AllArgsConstructor
public class SuscriptionController {
    private final SuscriptionService suscriptionService;

    // Souscrire un client à un pack
    @PostMapping("/subscribe")
    public ResponseEntity<Suscription> subscribeCustomerToPack(
            @RequestParam("customerId") Long customerId,
            @RequestParam("packId") Long packId) {
        Suscription suscription = suscriptionService.subscribeCustomerToPack(customerId, packId);
        return ResponseEntity.status(HttpStatus.CREATED).body(suscription);
    }

    // Récupérer un client avec ses souscriptions actives
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerWithActiveSuscriptions(
            @PathVariable("customerId") Long customerId) {
        Customer customer = suscriptionService.getCustomerWithActiveSusc(customerId);
        return ResponseEntity.ok(customer);
    }

    // Résilier l'abonnement d'un client
    @DeleteMapping("/cancel/{customerId}")
    public ResponseEntity<Void> cancelSubscription(
            @PathVariable("customerId") Long customerId) {
        suscriptionService.cancelSubscription(customerId);
        return ResponseEntity.noContent().build();
    }
}
