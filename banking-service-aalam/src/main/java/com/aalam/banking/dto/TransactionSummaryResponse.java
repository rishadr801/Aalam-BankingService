package com.aalam.banking.dto;

import java.time.LocalDate;

public class TransactionSummaryResponse {
    private double totalDebit;
    private double totalCredit;
    private LocalDate highestTransactionDay;
    private int transactionCount;
    public TransactionSummaryResponse(double totalDebit, double totalCredit, LocalDate highestTransactionDay, int transactionCount) {
        this.totalDebit = totalDebit; this.totalCredit = totalCredit; this.highestTransactionDay = highestTransactionDay; this.transactionCount = transactionCount;
    }
    public double getTotalDebit() { return totalDebit; }
    public void setTotalDebit(double totalDebit) { this.totalDebit = totalDebit; }
    public double getTotalCredit() { return totalCredit; }
    public void setTotalCredit(double totalCredit) { this.totalCredit = totalCredit; }
    public LocalDate getHighestTransactionDay() { return highestTransactionDay; }
    public void setHighestTransactionDay(LocalDate highestTransactionDay) { this.highestTransactionDay = highestTransactionDay; }
    public int getTransactionCount() { return transactionCount; }
    public void setTransactionCount(int transactionCount) { this.transactionCount = transactionCount; }
}
