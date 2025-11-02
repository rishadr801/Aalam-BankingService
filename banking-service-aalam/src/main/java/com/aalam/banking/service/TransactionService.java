package com.aalam.banking.service;

import com.aalam.banking.dto.TransactionSummaryResponse;
import com.aalam.banking.entity.Customer;
import com.aalam.banking.entity.Transaction;
import com.aalam.banking.exception.NoTransactionsFoundException;
import com.aalam.banking.exception.ResourceNotFoundException;
import com.aalam.banking.repository.TransactionRepository;
import com.aalam.banking.util.DateValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private CustomerService customerService;

    public TransactionSummaryResponse summaryFor(String customerId, int month, int year) {
        log.info("Starting transaction summary generation for customerId={}, month={}, year={}", customerId, month, year);

        // Validate customer
        Customer customer = customerService.findByCustomerId(customerId);

        LocalDate[] range = DateValidationUtil.getDateRange(month, year);
        LocalDate start = range[0];
        LocalDate end = range[1];
        
        List<Transaction> txns = transactionRepository.findByCustomerIdAndTxnDateBetween(customerId, start, end);

        if (txns.isEmpty()) {
            throw new NoTransactionsFoundException(
                    "No transactions found for customer " + customerId +
                    " in " + DateValidationUtil.formatYearMonth(year, month));
        }

        double totalDebit = txns.stream()
                .filter(t -> "DEBIT".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount).sum();

        double totalCredit = txns.stream()
                .filter(t -> "CREDIT".equalsIgnoreCase(t.getType()))
                .mapToDouble(Transaction::getAmount).sum();

        log.info("Transaction summary computed: totalDebit={}, totalCredit={}, totalTxns={}",
                totalDebit, totalCredit, txns.size());

        Map<LocalDate, Double> dailySum = txns.stream()
                .collect(Collectors.groupingBy(Transaction::getTxnDate, Collectors.summingDouble(Transaction::getAmount)));

        LocalDate highestDay = dailySum.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse(null);

        log.debug("Highest transaction day for {}: {}", customerId, highestDay);

        return new TransactionSummaryResponse(totalDebit, totalCredit, highestDay, txns.size());
    }

    public List<Map<String, Object>> topCustomersFor(int month, int year, int limit) {
        log.info("Fetching top {} customers for {}/{}", limit, month, year);
        
        LocalDate[] range = DateValidationUtil.getDateRange(month, year);
        LocalDate start = range[0];
        LocalDate end = range[1];

        List<Transaction> txns = transactionRepository.findByTxnDateBetween(start, end);

        if (txns.isEmpty()) {
            throw new NoTransactionsFoundException(
                    "No transactions found in " + DateValidationUtil.formatYearMonth(year, month));
        }

        Map<String, Double> volumeByCustomer = txns.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId,
                        Collectors.summingDouble(t -> Math.abs(t.getAmount()))));

        log.debug("Transaction volume grouped by customer: {}", volumeByCustomer);

        List<Map<String, Object>> result = volumeByCustomer.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .limit(limit)
                .map(e -> {
                    String customerId = e.getKey();
                    Customer customer = customerService.findByCustomerId(customerId);
                    Map<String, Object> map = new HashMap<>();
                    map.put("customerId", customerId);
                    map.put("name", customer.getName());
                    map.put("totalVolume", e.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        log.info("Top {} customers retrieved successfully", result.size());
        return result;
    }
}
