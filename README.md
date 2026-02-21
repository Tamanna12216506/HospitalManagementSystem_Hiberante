# 🏥 Hospital Management System ERP

*A Hibernate 7 + JPA-based Enterprise Mapping Practice Project*

------------------------------------------------------------------------

## 📌 Project Overview

This project is a backend-focused Hospital Management System ERP built
using **Java 21**, **Hibernate ORM 7.2.4**, **Jakarta Persistence (JPA
3.2)**, and **MySQL 8**.

The goal of this project is to deeply understand and implement:

-   JPA entity relationships
-   Cascade operations
-   Dirty checking
-   Lazy loading
-   Join tables
-   Bidirectional vs Unidirectional mappings
-   Transaction management
-   Clean layered architecture (Entity → Service → Config → App)

This project simulates real-world enterprise backend design while
focusing on Hibernate internals.

------------------------------------------------------------------------

## 🧠 What This Project Demonstrates

### ✅ 1️⃣ One-to-One (Unidirectional)

**Patient → MedicalRecord** - @OneToOne - @JoinColumn - Cascade ALL -
Lazy Fetch - Dirty checking - Cascade delete

### ✅ 2️⃣ One-to-Many (Bidirectional)

**Department ↔ Doctor** - @OneToMany(mappedBy) - @ManyToOne - FK
ownership - Helper methods - Proper relationship synchronization

### ✅ 3️⃣ One-to-Many (Unidirectional)

**Doctor → Appointment** - @OneToMany - @JoinColumn - No back
reference - FK placed in child table

### ✅ 4️⃣ One-to-One (Optional)

**Appointment → Prescription** - Optional association - Nullable FK -
Null-safe access - Cascade persist

### ✅ 5️⃣ Many-to-Many (Bidirectional)

**Doctor ↔ Patient** - @ManyToMany - @JoinTable - Proper owning side -
Safe cascade configuration (PERSIST + MERGE only) - Helper methods -
Join table management

------------------------------------------------------------------------

## 🏗 Architecture

org.hospitalmanagementsystemerp │ ├── entity → JPA entities (pure domain
models) ├── service → Business logic & CRUD operations ├── config →
Hibernate configuration utility └── App.java → Application bootstrap
runner

### 🔹 Layer Responsibilities

-   **Entity Layer** → Only mappings and relationships\
-   **Service Layer** → CRUD + transaction management\
-   **Config Layer** → Centralized EntityManagerFactory\
-   **App Layer** → Executes and tests services

------------------------------------------------------------------------

## 🛠 Technologies Used

-   Java 21\
-   Hibernate ORM 7.2.4.Final\
-   Jakarta Persistence API 3.2\
-   MySQL 8\
-   Maven\
-   IntelliJ IDEA

------------------------------------------------------------------------

## ⚙️ Key Hibernate Concepts Practiced

-   Persistence Unit configuration
-   Entity lifecycle states
-   Managed vs Detached entities
-   Dirty Checking
-   CascadeType behavior
-   LAZY fetching
-   Join table creation
-   Foreign key ownership rules
-   Optional vs Mandatory relationships
-   Many-to-Many cascade safety

------------------------------------------------------------------------

## 🗄 Database Schema Includes

-   patients
-   medical_records
-   departments
-   doctors
-   appointments
-   prescriptions
-   patient_doctors (join table)

------------------------------------------------------------------------

## 🚀 How to Run

1.  Clone repository\
2.  Configure MySQL database\
3.  Update persistence.xml credentials\
4.  Run App.java

Hibernate will auto-create tables if:

hibernate.hbm2ddl.auto=update

------------------------------------------------------------------------

## 🎯 Learning Objectives Achieved

-   Mastering JPA mappings
-   Understanding ownership & FK placement
-   Handling bidirectional consistency
-   Implementing service-layer architecture
-   Managing transactions manually
-   Debugging Hibernate exceptions
-   Working with real-world ORM patterns

------------------------------------------------------------------------

## 📚 Future Enhancements

-   Add DAO/Repository layer
-   Convert to Spring Boot
-   Add REST API layer
-   Implement validation
-   Add logging framework
-   Add DTO layer
-   Add pagination & advanced JPQL queries

------------------------------------------------------------------------

## 👨‍💻 Author

Developed as part of advanced Hibernate ORM practice and backend
architecture learning.
