# 🏦 Simple Bank Simulation (Android/Compose)

![Language](https://img.shields.io/badge/Language-Kotlin-100%25-purple)
![UI Framework](https://img.shields.io/badge/UI-Jetpack%20Compose-blue)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-green)
![Status](https://img.shields.io/badge/Status-Completed-brightgreen)

A modern Android banking simulation application built entirely with **Kotlin** and **Jetpack Compose**. This project serves as a practical demonstration of the **MVVM (Model-View-ViewModel)** architectural pattern, enforcing clean code principles, Unidirectional Data Flow (UDF), and secure state management.

---

## 📱 Screenshots

| 🔐 PIN Screen | 💰 Account Overview | 💸 Transactions |
|:---:|:---:|:---:|
| <img src="screenshots/pin_screen.png" width="250"/> | <img src="screenshots/account_screen.png" width="250"/> | <img src="screenshots/transaction_screen.png" width="250"/> |

---

## ✨ Features

* **🔐 Secure Authentication:** A dedicated PIN entry screen that restricts access to sensitive account data.
* **📊 Real-time Dashboard:** Live view of the current account balance and user details.
* **💳 Transaction Management:**
    * **Deposits:** Add funds with input validation.
    * **Withdrawals:** Deduct funds with business logic checks (e.g., preventing overdrafts).
* **⚡ Reactive UI:** Built with Jetpack Compose, the UI automatically reacts to state changes emitted by the ViewModel.

---

## 🏗️ Architecture & Tech Stack

This project follows the recommended **Modern Android Architecture**:

* **Architecture:** MVVM (Model-View-ViewModel)
* **UI Pattern:** Unidirectional Data Flow (UDF)

### The MVVM Implementation:
1.  **Model:** Data classes representing the `Account` and `Transaction` logic.
2.  **ViewModel:** Manages the UI state using `StateFlow`. It handles business logic (like checking PIN correctness or validating balance) and exposes immutable state to the View.
3.  **View (Compose):** Stateless Composables that observe the ViewModel's state and trigger events (e.g., `onDepositClick`) back to the ViewModel.

### 🛠️ Tech Stack
* **Language:** [Kotlin](https://kotlinlang.org/)
* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
* **Concurrency:** Coroutines & Flow
* **Testing:** JUnit (Unit Testing for ViewModels)

---

## 📂 Project Structure

```text
com.example.banksimulation
├── data/                # Data models (Account, Transaction)
├── ui/                  # Jetpack Compose UI Layer
│   ├── theme/           # Color, Type, and Shape definitions
│   ├── auth/            # PinScreen Composable
│   ├── dashboard/       # AccountScreen & Transaction components
│   └── components/      # Reusable UI widgets (Buttons, InputFields)
├── viewmodel/           # MainViewModel & State Management
└── utils/               # Constants and Extension functions
