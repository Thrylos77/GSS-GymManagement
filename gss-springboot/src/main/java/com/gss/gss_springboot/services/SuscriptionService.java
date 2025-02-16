package com.gss.gss_springboot.services;

import com.gss.gss_springboot.models.Customer;
import com.gss.gss_springboot.models.Suscription;

public interface SuscriptionService {
    Suscription subscribeCustomerToPack(Long idCust, Long packId);
    Customer getCustomerWithActiveSusc(Long idCust);
    void cancelSubscription(Long idCust);
}
