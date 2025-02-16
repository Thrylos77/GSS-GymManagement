package com.gss.gss_springboot.services;

import java.util.List;
import java.util.Optional;

import com.gss.gss_springboot.models.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Optional<Customer> getCustomerById(Long id);
    List<Customer> readAllCustomers();
    void deleteCustomer(Long id);
    Customer updateCustomer(Long id, Customer customer);
    List<Customer> findCustomerByName(String first_name, String last_name);

}
