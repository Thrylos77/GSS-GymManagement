package com.gss.gss_springboot.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gss.gss_springboot.models.Customer;
import com.gss.gss_springboot.repositories.CustomerRepository;
import com.gss.gss_springboot.services.CustomerService;

@Service
// @AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    // Dependance injection via the contructor
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    // CREATE
    @Override
    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }
    // READ
    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    @Override
    public List<Customer> readAllCustomers(){
        return customerRepository.findAll();
    }
    @Override
    public List<Customer> findCustomerByName(String first_name, String last_name){
        return customerRepository.findByFirstNameAndLastName(first_name, last_name);
    }

    // UPDATE
    @Override
    public Customer updateCustomer(Long id, Customer customer){
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        return existingCustomer
                 .map(c-> {
                c.setLastName(customer.getLastName());
                c.setFirstName(customer.getFirstName());
                c.setPhoneNumber(customer.getPhoneNumber());
                c.setRegistrationDate(customer.getRegistrationDate());
                c.setActiveSuscription(customer.isActiveSuscription());
                return customerRepository.save(c);
             }).orElseThrow(()-> new RuntimeException("Client not found with id: " + id));
    }
    // DELETE
    @Override
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }
    
}
