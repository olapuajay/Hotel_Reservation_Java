# Hotel Reservation Application

A command-line based **Hotel Reservation Application** built in **Java** following **Object-Oriented Programming (OOP)** principles and a **layered architecture**. The application allows customers to create accounts, search for available rooms, make reservations, and enables administrators to manage rooms, customers, and reservations.

## 🚀 Features

### 👤 Customer Features
- Create a customer account
- Search for available rooms
- Reserve a room
- View personal reservations
- Receive recommended room availability if the selected dates are unavailable

### 🛠️ Admin Features
- View all customers
- View all rooms
- View all reservations
- Add paid or free rooms
- Prevent duplicate room creation

---

# 🏗️ Project Architecture

The application follows a layered architecture to maintain separation of concerns.

```
Hotel Reservation Application
│
├── UI Layer
│   ├── MainMenu
│   └── AdminMenu
│
├── API Layer
│   ├── HotelResource
│   └── AdminResource
│
├── Service Layer
│   ├── CustomerService
│   └── ReservationService
│
└── Model Layer
    ├── Customer
    ├── IRoom
    ├── Room
    ├── FreeRoom
    ├── Reservation
    └── RoomType
```

---

# 📁 Project Structure

```
HotelReservationApplication/
│
├── api/
│   ├── AdminResource.java
│   └── HotelResource.java
│
├── model/
│   ├── Customer.java
│   ├── FreeRoom.java
│   ├── IRoom.java
│   ├── Reservation.java
│   ├── Room.java
│   └── RoomType.java
│
├── service/
│   ├── CustomerService.java
│   └── ReservationService.java
│
├── AdminMenu.java
├── MainMenu.java
└── HotelApplication.java
```

---

# 💻 Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Collections Framework
- Regular Expressions (Regex)
- Date & Calendar API
- Command Line Interface (CLI)

---

# 📚 OOP Concepts Implemented

- Encapsulation
- Abstraction
- Inheritance
- Polymorphism
- Interfaces
- Singleton Design Pattern
- Layered Architecture

---

# 📦 Model Classes

### Customer
- First Name
- Last Name
- Email Validation using Regex

### Room
- Room Number
- Price
- Room Type

### FreeRoom
- Extends `Room`
- Automatically sets room price to `0`

### Reservation
- Customer
- Room
- Check-in Date
- Check-out Date

### RoomType
Enumeration containing:
- SINGLE
- DOUBLE

### IRoom
Interface defining room behavior.

---

# ⚙️ Service Layer

## CustomerService
Handles customer-related operations:

- Add customer
- Get customer
- Retrieve all customers

## ReservationService
Handles reservation-related operations:

- Add room
- Get room
- Reserve room
- Search available rooms
- Search recommended rooms
- View customer reservations
- Display all reservations

---

# 🔗 API Layer

## HotelResource
Acts as an intermediary between the UI and services.

Functions include:

- Create customer
- Find room
- Reserve room
- Get customer reservations

## AdminResource

Provides administrative operations:

- Add rooms
- View all rooms
- View all customers
- View all reservations

---

# 🖥️ User Interface

## Main Menu

```
1. Find and reserve a room
2. See my reservations
3. Create an account
4. Admin
5. Exit
```

## Admin Menu

```
1. See all Customers
2. See all Rooms
3. See all Reservations
4. Add a Room
5. Back to Main Menu
```

---

# ✅ Validations Implemented

## Customer Validation

- Email format validation using Regular Expressions
- Prevent duplicate customer accounts

Example:

```
user@example.com
```

---

## Room Validation

- Room number cannot be empty
- Room numbers must be unique
- Room price cannot be negative
- Room type validation (SINGLE or DOUBLE only)

---

## Reservation Validation

- Check-in date cannot be in the past
- Check-out date must be after check-in date
- Minimum stay of one day
- Prevent overlapping reservations
- Prevent double booking

---

# 🔍 Room Search

The application searches available rooms based on:

- Check-in Date
- Check-out Date

If rooms are unavailable, the application automatically searches for available rooms **7 days later** and recommends them to the user.

Example:

```
Requested:
10 Apr → 12 Apr

Recommended:
17 Apr → 19 Apr
```

Users can directly reserve the recommended room.

---

# 🆓 Free Room Support

The application supports free rooms using inheritance.

Features:

- Price automatically set to ₹0
- Displayed as **Free**
- Available during room searches
- Can be reserved like any other room

---

# 🗂️ Collections Used

| Data | Collection |
|------|------------|
| Customers | HashMap |
| Rooms | HashMap |
| Reservations | ArrayList |

---

# 🧩 Design Patterns Used

## Singleton Pattern

Implemented in:

- CustomerService
- ReservationService
- HotelResource
- AdminResource

Ensures only one instance of each service/resource exists throughout the application.

---

# 🛡️ Exception Handling

The application gracefully handles invalid inputs using:

- try-catch blocks
- IllegalArgumentException

Examples:

- Invalid email
- Duplicate room
- Duplicate customer
- Invalid room type
- Invalid reservation dates
- Room already booked

---

# 📋 Sample Workflow

### Customer

```
Create Account
      ↓
Search Rooms
      ↓
Select Room
      ↓
Reserve Room
      ↓
View Reservation
```

### Admin

```
Login Admin Menu
      ↓
Add Rooms
      ↓
View Customers
      ↓
View Rooms
      ↓
View Reservations
```

---

# ▶️ How to Run

1. Clone the repository

```bash
git clone https://github.com/your-username/hotel-reservation-application.git
```

2. Open the project in IntelliJ IDEA (or any Java IDE).

3. Run:

```
HotelApplication.java
```

---

# 🎯 Learning Outcomes

This project demonstrates practical implementation of:

- Java Fundamentals
- Object-Oriented Programming
- Interfaces and Inheritance
- Polymorphism
- Collections Framework
- Singleton Design Pattern
- Layered Architecture
- Exception Handling
- Input Validation
- Command Line Applications
- Date and Calendar Operations

---

# 📌 Future Improvements

- Database integration (MySQL/PostgreSQL)
- Spring Boot REST API
- Authentication & Authorization
- Room cancellation
- Payment integration
- Room categories and pricing
- JUnit testing
- Logging framework
- File-based or database persistence

---

# 👨‍💻 Author

**Ajay**

Backend Developer | Java | Spring Boot | SQL

---
