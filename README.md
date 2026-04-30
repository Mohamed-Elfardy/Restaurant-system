<div align="center">

# 🍽️ Restaurant Management System

### A fully custom-built, console-based Java application — engineered from scratch.

![Java](https://img.shields.io/badge/Java-OOP-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Build](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

> A comprehensive, modular Restaurant Management System built entirely from the ground up using core Java and Object-Oriented Programming principles — no frameworks, no external dependencies, pure engineering.

</div>

---

## 📌 Table of Contents

- [Overview](#-overview)
- [Features](#-features)
- [System Architecture](#-system-architecture)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [Project Structure](#-project-structure)
- [Development Team](#-development-team)

---

## 🧭 Overview

The **Restaurant Management System** is a full-featured, console-based application developed entirely from scratch by a dedicated team of six engineers. It is designed to handle every operational aspect of a restaurant — from user authentication and order processing to loyalty programs, billing, and automated notifications.

Every component — including ID generation, data seeding, validation logic, and display utilities — was hand-crafted without relying on any third-party libraries or frameworks, demonstrating a strong command of Java OOP fundamentals and clean software design.

---

## ✨ Features

### 🔐 Authentication & Configuration
- Secure user login and role-based access control
- Centralized application configuration management
- Custom ID generation engine for all entities
- Automated data seeding for initial system setup

### 👥 User & Employee Management
- Full CRUD operations for **Customer** and **Employee** entities
- Dedicated service layers with clean separation of concerns
- Role differentiation between customers, staff, and administrators

### 🍕 Meal & Menu Management
- Structured **Meal**, **Category**, and related entity models
- Full menu organization with categorized item listings
- Flexible meal configuration and availability management

### 🧾 Order Processing
- End-to-end **Order** and **OrderItem** lifecycle management
- Real-time order tracking and status updates
- Multi-item order composition with quantity control

### 💳 Billing & Payments
- Automated **Billing** generation per order
- Multiple **Payment** method support
- Integrated **Offers** and **Gifts** system for discounts and promotions

### 📊 Reports & Notifications
- Business intelligence via built-in **Reporting** module
- Automated **Notification** system for order and system events
- Sales and activity summaries

### 🎯 Loyalty, Marketing & Rewards
- Full **Loyalty Program** with point accumulation and redemption
- **Marketing Campaign** management
- Structured **Reward Program** engine for customer retention

### 🖥️ Console Interface & Utilities
- Clean, navigable **Console Display** system
- Robust **Input Validation** for all user interactions
- Reusable **Menu Utilities** for consistent UI/UX across the app

---

## 🏗️ System Architecture

The system follows a clean, layered architecture pattern:

```
┌─────────────────────────────────────────────┐
│              Console Interface               │
│     (Display, Menus, Input Validation)       │
├─────────────────────────────────────────────┤
│              Service Layer                   │
│  (Auth, Customer, Employee, Order, Billing,  │
│   Loyalty, Marketing, Notifications, etc.)   │
├─────────────────────────────────────────────┤
│              Entity / Model Layer            │
│  (User, Meal, Order, OrderItem, Invoice,     │
│   Reward, Offer, Gift, Notification, etc.)   │
├─────────────────────────────────────────────┤
│          Config, Seeding & Utilities         │
│      (AppConfig, DataSeeder, IdGenerator)    │
└─────────────────────────────────────────────┘
```

**Design Principles Applied:**
- **Encapsulation** — All entities are self-contained with controlled access
- **Inheritance** — Shared behavior abstracted into base classes
- **Polymorphism** — Service interfaces with flexible implementations
- **Abstraction** — Clean separation between UI, logic, and data layers
- **Single Responsibility** — Each class and module has one clear purpose

---

## 🛠️ Tech Stack

| Technology | Details |
|---|---|
| **Language** | Java (JDK 8+) |
| **Paradigm** | Object-Oriented Programming (OOP) |
| **Interface** | Console / Terminal (CLI) |
| **Build Tool** | Manual / IDE (IntelliJ IDEA / Eclipse) |
| **Dependencies** | None — built entirely from scratch |
| **Version Control** | Git & GitHub |

> ⚠️ **No external libraries, frameworks, or build tools are required.** The entire system is implemented using core Java only.

---

## 🚀 Getting Started

### Prerequisites

- **Java JDK 8 or higher** installed on your machine
- A terminal / command prompt
- _(Optional)_ An IDE such as IntelliJ IDEA or Eclipse

### Installation

**1. Clone the repository:**
```bash
git clone https://github.com/your-org/restaurant-management-system.git
cd restaurant-management-system
```

**2. Compile the project:**
```bash
javac -d out/production src/**/*.java
```
> If using an IDE, simply import the project and let the IDE handle compilation.

**3. Run the application:**
```bash
java -cp out/production Main
```

### Default Credentials _(Seeded Data)_

The system auto-seeds initial data on first run. Use the seeded admin credentials displayed in the console to log in and explore all modules.

---

## 📁 Project Structure

```
restaurant-management-system/
│
├── src/
│   ├── config/              # Application configuration
│   ├── auth/                # Authentication & authorization
│   ├── entities/            # Core user and domain entities
│   ├── seeding/             # Data seeding utilities
│   ├── utils/               # ID generation & shared helpers
│   │
│   ├── customer/            # Customer entity & service
│   ├── employee/            # Employee entity & service
│   │
│   ├── meal/                # Meal & category entities/services
│   ├── order/               # Order & OrderItem processing
│   │
│   ├── billing/             # Billing & invoice management
│   ├── payment/             # Payment processing
│   ├── offers/              # Offers & promotional gifts
│   ├── notifications/       # Notification system
│   ├── reports/             # Reporting & analytics
│   │
│   ├── loyalty/             # Loyalty program
│   ├── marketing/           # Marketing campaigns
│   ├── rewards/             # Reward programs
│   │
│   ├── display/             # Console UI rendering
│   ├── validation/          # Input validation logic
│   ├── menu/                # Menu navigation utilities
│   │
│   └── Main.java            # Application entry point
│
└── README.md
```

---

## 👨‍💻 Development Team

This system was designed and built from scratch by a team of six dedicated engineers. Each member owned their modules end-to-end — from entity design to service implementation.

---

### 🧑‍💼 Mohamed Yasser — *Team Lead & Core Systems Architect*
> **Modules:** Config · Authentication · Core User Entities · Data Seeding · ID Generation · Main Application (co-lead)

Laid the foundation of the entire system. Responsible for the application's configuration layer, the authentication and authorization system, all core user entity definitions, automated data seeding on startup, and the custom ID generation engine used across all modules. Co-led the final main application wiring and execution.

---

### 👨‍💻 Mahmoud Gad — *Customer & Employee Systems Engineer*
> **Modules:** Customer Service · Customer Entity · Employee Service · Employee Entity

Designed and implemented the full lifecycle management for both customer and employee records, including their data models, business logic, and dedicated service layers. Ensured clean role-based separation between user types.

---

### 👨‍💻 Marwan — *Menu & Order Domain Engineer*
> **Modules:** Meal · Order · OrderItem · Categories · Related Services

Built the core operational backbone of the restaurant. Designed the meal and category data models, and implemented the full order management pipeline — from order creation and item composition to status tracking and fulfillment logic.

---

### 👨‍💻 Abdelrahman — *Financial & Operations Systems Engineer*
> **Modules:** Billing · Reports · Notifications · Payments · Offers · Gifts

Engineered the financial and operational intelligence layer of the system. Implemented automated billing generation, multi-method payment processing, reporting and analytics, the notification dispatch system, and the offers and gifts promotion engine.

---

### 👨‍💻 Amr Khaled — *Loyalty & Marketing Systems Engineer*
> **Modules:** Loyalty Program · Marketing Campaigns · Reward Programs

Built the customer engagement and retention arm of the system. Designed and implemented the full loyalty point engine, reward program structures, and marketing campaign management — enabling the restaurant to run promotions and incentivize repeat customers.

---

### 👨‍💻 Youssef — *Console UI & Validation Engineer*
> **Modules:** Console Display · Input Validation · Menu Utilities · Main Application (co-lead)

Owned the entire user-facing layer of the application. Developed the console display rendering system, all input validation logic to ensure data integrity, and the reusable menu utility components that provide consistent navigation throughout the app. Co-led final main application execution with Mohamed Yasser.

---

<div align="center">

**Built with ☕ Java and a lot of hard work — entirely from scratch.**

*© 2024 Restaurant Management System Team. All rights reserved.*

</div>
