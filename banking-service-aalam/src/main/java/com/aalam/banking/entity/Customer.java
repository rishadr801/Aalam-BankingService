package com.aalam.banking.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = "aadhar_number"))
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", unique = true)
    private String customerId;
    
    @NotBlank(message = "Customer name cannot be blank")
    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;
    
    @NotBlank(message = "Aadhar number cannot be blank")
    @Column(name = "aadhar_number", nullable = false, unique = true)
    private String aadharNumber;

    @Column(nullable = false)
    private Double income;
    
    @NotNull(message = "Date of birth is required")
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getAadharNumber() { return aadharNumber; }
    public void setAadharNumber(String aadharNumber) { this.aadharNumber = aadharNumber; }
    public Double getIncome() { return income; }
    public void setIncome(Double income) { this.income = income; }
    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }
}
