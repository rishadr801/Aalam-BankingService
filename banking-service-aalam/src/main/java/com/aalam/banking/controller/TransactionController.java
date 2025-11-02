package com.aalam.banking.controller;

import com.aalam.banking.dto.TransactionSummaryResponse;
import com.aalam.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/summary/{customerId}")
    public ResponseEntity<TransactionSummaryResponse> summary(@PathVariable String customerId,
                                                               @RequestParam int month,
                                                               @RequestParam int year) {
        return ResponseEntity.ok(transactionService.summaryFor(customerId, month, year));
    }
}
