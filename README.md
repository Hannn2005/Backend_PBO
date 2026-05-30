# Backend PBO - Spring Boot

REST API backend built with Spring Boot and PostgreSQL (Neon).

## Prerequisites

- Java 21
- Maven

## Setup

### 1. Clone the repository

### 2. Add dependency di `pom.xml` untuk read .env

Tambahkan dependency berikut di dalam `<dependencies>`:

```
<dependency>
    <groupId>me.paulschwarz</groupId>
    <artifactId>spring-dotenv</artifactId>
    <version>4.0.0</version>
</dependency>
```

### 3. Buat file `.env` di root project -> Minta Developer 

example
```env
DB_URL=jdbc:postgresql://ep-xxxx.us-east-1.aws.neon.tech/dbname?sslmode=require
DB_USERNAME=your_neon_username
DB_PASSWORD=your_neon_password
```

### 4. Jalankan aplikasi
