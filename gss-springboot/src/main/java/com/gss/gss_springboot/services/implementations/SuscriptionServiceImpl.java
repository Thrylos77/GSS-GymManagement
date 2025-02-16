package com.gss.gss_springboot.services.implementations;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gss.gss_springboot.models.Customer;
import com.gss.gss_springboot.models.Pack;
import com.gss.gss_springboot.models.Suscription;
import com.gss.gss_springboot.repositories.CustomerRepository;
import com.gss.gss_springboot.repositories.PackRepository;
import com.gss.gss_springboot.repositories.SuscriptionRepository;
import com.gss.gss_springboot.services.SuscriptionService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuscriptionServiceImpl implements SuscriptionService{
    
    private final SuscriptionRepository suscriptionRepository;
    private final CustomerRepository customerRepository;
    private final PackRepository packRepository;

    @Override
    public Suscription subscribeCustomerToPack(Long idCust, Long idPack){
        Customer customer = customerRepository.findById(idCust)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + idCust));
        Pack pack = packRepository.findById(idPack)
                .orElseThrow(() -> new RuntimeException("Pack not found with id: " + idPack));
        
        Suscription suscription = new Suscription();
        suscription.setStartDate(LocalDate.now());
        suscription.setCustomer(customer);
        suscription.setPack(pack);
        
        // Sauvegarder la souscription
        Suscription savedSuscription = suscriptionRepository.save(suscription);
        
        // Mettre à jour le flag ou la collection de souscriptions du customer si besoin
        customer.getSuscriptions().add(savedSuscription);
        customer.setActiveSuscription(true); // Par exemple, mettre à jour ce flag
        customerRepository.save(customer);
        
        return savedSuscription;
    }

    @Override
    public Customer getCustomerWithActiveSusc(Long idCust) {
        Customer customer = customerRepository.findById(idCust)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + idCust));
        // Ici, on peut filtrer les souscriptions actives si nécessaire.
        return customer;
    }

    @Override
    public void cancelSubscription(Long idCust) {
        Customer customer = customerRepository.findById(idCust)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + idCust));
        
        // récupérer toutes les souscriptions actives via une méthode du repository
        List<Suscription> suscriptions = suscriptionRepository.findByCustomerAndCustomerActiveSuscriptionTrue(customer);
        if (suscriptions.isEmpty()) {
            throw new RuntimeException("No active subscription found for customer id: " + idCust);
        }
        
        // Supprimer toutes les souscriptions actives (ou mettre à jour leur statut)
        suscriptions.forEach(suscriptionRepository::delete);
        
        // Mettre à jour le flag du client
        customer.setActiveSuscription(false);
        customerRepository.save(customer);
    }
}
