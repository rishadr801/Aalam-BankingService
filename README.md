# 🏦 Banking Service API

A Spring Boot–based Banking Service application designed to handle customer onboarding, loan eligibility checks, transaction summaries, and top customer reports.

---

## ⚙️ Tech Stack
- Java 8
- Spring Boot 2.7.x
- MySQL
- Maven
- JPA / Hibernate
- REST API (JSON)

---

## 🧾 Prerequisites
- Java 8 JDK
- Maven
- MySQL running locally with a database named `banking`

---

## 💾 MySQL Setup (Example)
```sql
CREATE DATABASE banking;
```
> Adjust credentials in `application.properties` if needed (default: root / rishad).

---

## 🚀 Run the Application
```bash
mvn clean package
java -jar target/banking-service-1.0.0.jar
```
Application starts on:
```
http://localhost:8080
```

---

## 🧩 APIs

### 1️⃣ Create Customer
**Endpoint:**  
`POST /customers`

**Request URL:**  
```
http://localhost:8080/customers
```

**Sample Request Body (JSON):**
```json
{
  "name": "Ravi Kumar",
  "email": "ravi.kumar@example.com",
  "income": 55000,
  "aadharNumber": "123456789012",
  "dob": "1995-06-15"
}
```

---

### 2️⃣ Check Loan Eligibility
**Endpoint:**  
`POST /loans/check`

**Request URL:**  
```
http://localhost:8080/loans/check
```

**Sample Request Body (JSON):**
```json
{
  "customerId": "CUST20250001",
  "loanAmount": 400000,
  "loanType": "PERSONAL"
}
```

---

### 3️⃣ Transaction Summary
**Endpoint:**  
`GET /transactions/summary/{customerId}?month={month}&year={year}`

**Request URL Example:**  
```
http://localhost:8080/transactions/summary/CUST20250001?month=10&year=2025
```

**Sample Response:**
```json
{
  "totalDebit": 40000,
  "totalCredit": 25000,
  "highestTransactionDay": "2025-10-05",
  "transactionCount": 12
}
```

---

### 4️⃣ Top Customers Report
**Endpoint:**  
`GET /reports/topCustomers?limit={limit}&month={month}&year={year}`

**Request URL Example:**  
```
http://localhost:8080/reports/topCustomers?limit=3&month=10&year=2025
```

**Sample Response:**
```json
[
  {"customerId": "CUST20250003", "name": "Ravi", "totalVolume": 95000},
  {"customerId": "CUST20250001", "name": "Priya", "totalVolume": 87000}
]
```

---

## 🗄️ SQL Seeding (Sample Data)
The application automatically seeds data using the `src/main/resources/data.sql` file.

### ✅ Auto-Inserted Data Includes:
- 5 Customers
- 5 Loans
- 10 Transactions

This data is automatically loaded during startup, so APIs can be tested immediately.

---

## 🧰 Database Queries
```sql
SELECT * FROM customers;
SELECT * FROM loans;
SELECT * FROM transactions;

TRUNCATE TABLE transactions;
TRUNCATE TABLE loans;
TRUNCATE TABLE customers;
```

---

## ⚠️ Note
- Project implemented using Java 8
