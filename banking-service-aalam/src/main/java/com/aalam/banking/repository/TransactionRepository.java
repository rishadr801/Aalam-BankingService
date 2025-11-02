package com.aalam.banking.repository;

import com.aalam.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerIdAndTxnDateBetween(String customerId, LocalDate start, LocalDate end);
    List<Transaction> findByTxnDateBetween(LocalDate start, LocalDate end);
}
