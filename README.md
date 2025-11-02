# Aalam-BankingService
Spring Boot project for basic banking operations

# Banking Service (Java 8 / Spring Boot 2.7.x)

## Prerequisites
- Java 8 JDK
- Maven
- MySQL running locally with DB `banking`

## MySQL setup (example)
CREATE DATABASE banking;
-- Adjust user if needed; current application.properties uses root / rishad

## Run
mvn clean package
java -jar target/banking-service-1.0.0.jar

APIs:
- POST /customers
- POST /loans/check
- GET /transactions/summary/{customerId}?month=10&year=2025
- GET /reports/topCustomers?limit=3&month=10&year=2025

## SQL Seeding (Sample Data)
Sample records will be automatically inserted during application startup.

- Customers

INSERT INTO customers (id, customer_id, name, email, aadhar_number, income, dob) VALUES
(1, 'CUST202500001', 'Priya', 'priya@gmail.com', '111122223333', 50000, '1990-05-15'),
(2, 'CUST202500002', 'Ravi', 'ravi@gmail.com', '222233334444', 80000, '1985-03-10'),
(3, 'CUST202500003', 'Kumar', 'kumar@gmail.com', '333344445555', 25000, '1998-07-20'),
(4, 'CUST202500004', 'Anita', 'anita@gmail.com', '444455556666', 120000, '1980-11-02'),
(5, 'CUST202500005', 'Suresh', 'suresh@gmail.com', '555566667777', 60000, '1992-09-25');

- Loans

INSERT INTO loans (id, customer_id, amount, loan_type, created_on, status) VALUES
(1, 'CUST202500001', 200000, 'PERSONAL', '2025-01-10', 'APPROVED'),
(2, 'CUST202500002', 500000, 'HOME', '2024-12-15', 'APPROVED'),
(3, 'CUST202500005', 200000, 'PERSONAL', '2024-12-20', 'APPROVED'),
(4, 'CUST202500005', 100000, 'PERSONAL', '2024-05-20', 'APPROVED'),
(5, 'CUST202500005', 300000, 'PERSONAL', '2025-01-15', 'APPROVED');

- Transactions

INSERT INTO transactions (id, customer_id, amount, type, txn_date) VALUES
(1, 'CUST202500001', 5000, 'DEBIT', '2025-10-05'),
(2, 'CUST202500001', 8000, 'CREDIT', '2025-10-03'),
(3, 'CUST202500002', 15000, 'DEBIT', '2025-10-05'),
(4, 'CUST202500003', 2000, 'CREDIT', '2025-10-10'),
(5, 'CUST202500004', 25000, 'DEBIT', '2025-10-12'),
(6, 'CUST202500005', 12000, 'CREDIT', '2025-10-01'),
(7, 'CUST202500001', 3000, 'DEBIT', '2025-10-05'),
(8, 'CUST202500002', 20000, 'CREDIT', '2025-10-02'),
(9, 'CUST202500005', 5000, 'DEBIT', '2025-10-05'),
(10, 'CUST202500004', 8000, 'CREDIT', '2025-10-20');


## Viewing & Removing Data

SELECT * FROM customers;
SELECT * FROM loans;
SELECT * FROM transactions;

TRUNCATE TABLE transactions;
TRUNCATE TABLE loans;
TRUNCATE TABLE customers;
