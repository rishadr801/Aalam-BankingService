package com.aalam.banking.repository;

import com.aalam.banking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String customerId);
    boolean existsByAadharNumber(String aadharNumber);
}
