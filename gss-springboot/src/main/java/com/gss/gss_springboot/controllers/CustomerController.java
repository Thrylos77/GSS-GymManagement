package com.gss.gss_springboot.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gss.gss_springboot.models.Customer;
import com.gss.gss_springboot.services.CustomerService;

import lombok.AllArgsConstructor;





@RestController
@RequestMapping("/gss/customers")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @PostMapping("/createCustomer")
    /* Using ResponseEntity is generaly good because 
    she give a best control with the HTTP reponse and so
    facilitate errors management */
    public ResponseEntity<Customer> createCustomer(@Validated @RequestBody Customer customer) {
        Customer createCust = customerService.createCustomer(customer);
        return ResponseEntity.ok(createCust);
    }
    
    @GetMapping("/listCustomers")  // Show customers list
    public ResponseEntity<List<Customer>> readAllCustomers() {
        List<Customer> customers = customerService.readAllCustomers();
        return ResponseEntity.ok(customers);
    }
    
    @GetMapping("/searchCustomer") // Search customer by his name
    public ResponseEntity<List<Customer>> searchCustomer(
            @RequestParam String first_name, 
            @RequestParam String last_name) {
        List<Customer> customers = customerService.findCustomerByName(first_name, last_name);
        if(customers.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }
    
    @PutMapping("/updateCustomer/{id}") // Modify a customer informations
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Validated @RequestBody Customer customer) {        
        try {
            Customer updateCustomer = customerService.updateCustomer(id, customer);
            return ResponseEntity.ok(updateCustomer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build(); // return a 404 code if the client not found
        }
    }

    @DeleteMapping("/deleteCustomer/{id}") // Delete a customer
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();       
    }
}
