package com.aalam.banking.repository;

import com.aalam.banking.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByCustomerId(String customerId);
}
