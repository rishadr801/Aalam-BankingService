package com.aalam.banking.service;

import com.aalam.banking.entity.Customer;
import com.aalam.banking.exception.ResourceNotFoundException;
import com.aalam.banking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomerService {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer customer) {
    	
    	log.info("Creating new customer: {}", customer.getName());
    	
    	if (customer.getEmail() == null || !EMAIL_PATTERN.matcher(customer.getEmail()).matches()) {
            throw new IllegalArgumentException("Invalid email format (must be a valid address like user@gmail.com)");
        }
    	
        if (customer.getIncome() == null || customer.getIncome() <= 0) {
            throw new IllegalArgumentException("Income must be greater than 0");
        }
        if (customer.getDob() == null || customer.getDob().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("DOB cannot be in the future");
        }
        if (customerRepository.existsByAadharNumber(customer.getAadharNumber())) {
            throw new IllegalArgumentException("Aadhar number must be unique");
        }

        Customer saved = customerRepository.save(customer);
        String generated = String.format("CUST2025%05d", saved.getId());
        saved.setCustomerId(generated);
        customerRepository.save(saved);
        
        log.info("Customer created successfully with ID: {}", generated);
        return saved;
    }

    public Customer findByCustomerId(String customerId) {
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found: " + customerId));
    }
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.(com|in|net|org)$"
    );
}
