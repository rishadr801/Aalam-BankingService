package com.aalam.banking.controller;

import com.aalam.banking.dto.LoanCheckRequest;
import com.aalam.banking.dto.LoanCheckResponse;
import com.aalam.banking.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/check")
    public ResponseEntity<LoanCheckResponse> check(@RequestBody LoanCheckRequest req) {
        LoanCheckResponse res = loanService.checkEligibility(req);
        return ResponseEntity.ok(res);
    }
}
