package com.aalam.banking.dto;

public class LoanCheckResponse {
    private boolean eligible;
    private long maxEligibleAmount;
    private String message;
    public LoanCheckResponse() {}
    public LoanCheckResponse(boolean eligible, long maxEligibleAmount, String message) {
        this.eligible = eligible; this.maxEligibleAmount = maxEligibleAmount; this.message = message;
    }
    public boolean isEligible() { return eligible; }
    public void setEligible(boolean eligible) { this.eligible = eligible; }
    public long getMaxEligibleAmount() { return maxEligibleAmount; }
    public void setMaxEligibleAmount(long maxEligibleAmount) { this.maxEligibleAmount = maxEligibleAmount; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
