package com.aalam.banking.controller;

import com.aalam.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/topCustomers")
    public ResponseEntity<List<Map<String, Object>>> topCustomers(@RequestParam(defaultValue = "3") int limit,
                                                                   @RequestParam int month,
                                                                   @RequestParam int year) {
        return ResponseEntity.ok(transactionService.topCustomersFor(month, year, limit));
    }
}
