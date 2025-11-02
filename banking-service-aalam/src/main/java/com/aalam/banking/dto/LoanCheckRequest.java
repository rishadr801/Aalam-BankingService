package com.aalam.banking.dto;

public class LoanCheckRequest {
    private String customerId;
    private Long loanAmount;
    private String loanType;
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public Long getLoanAmount() { return loanAmount; }
    public void setLoanAmount(Long loanAmount) { this.loanAmount = loanAmount; }
    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }
}
