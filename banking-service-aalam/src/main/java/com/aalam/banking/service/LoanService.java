package com.aalam.banking.service;

import com.aalam.banking.dto.LoanCheckRequest;
import com.aalam.banking.dto.LoanCheckResponse;
import com.aalam.banking.entity.Customer;
import com.aalam.banking.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoanService {
	
	private static final Logger log = LoggerFactory.getLogger(LoanService.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoanRepository loanRepository;

    public LoanCheckResponse checkEligibility(LoanCheckRequest req) {
        Customer c = customerService.findByCustomerId(req.getCustomerId());
        double income = c.getIncome();
        log.debug("Customer income retrieved: ₹{}", income);

        if (income < 30000) {
            return new LoanCheckResponse(false, 0L,
                    "Not eligible: income is below ₹30,000.");
        }

        long existingLoans = loanRepository.countByCustomerId(req.getCustomerId());
        log.debug("Existing active loans for {}: {}", req.getCustomerId(), existingLoans);
        
        if (existingLoans > 2) {
            return new LoanCheckResponse(false, 0L,
                    "Not eligible: customer already has more than 2 active loans.");
        }

        long maxEligible;
        if (income <= 70000) {
            maxEligible = 500000L;
        } else {
            maxEligible = 1500000L;
        }

        boolean eligible = req.getLoanAmount() <= maxEligible;
        String message;

        if (eligible) {
            message = String.format(
                    "Eligible for %s loan up to ₹%,d.",
                    req.getLoanType().toLowerCase(), maxEligible
            );
        } else {
            message = String.format(
                    "Not eligible: requested amount ₹%,d exceeds your eligibility limit of ₹%,d.",
                    req.getLoanAmount(), maxEligible
            );
        }
        log.debug("Loan Service End");
        return new LoanCheckResponse(eligible, maxEligible, message);
    }
}
