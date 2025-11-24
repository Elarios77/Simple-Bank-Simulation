# 🏦 Simple Bank Simulation (Android/Compose)

A modern Android banking simulation application built entirely with **Kotlin** and **Jetpack Compose**. This project serves as a practical demonstration of the **MVVM (Model-View-ViewModel)** architectural pattern, enforcing clean code principles, Unidirectional Data Flow (UDF), and secure state management.

---

## 📱 Screenshots

| 🔐 PIN Screen | 💰 Account Overview | 💸 deposit |
|:---:|:---:|:---:|
| <img src="screenshots/pin.jpg" width="250"/> | <img src="screenshots/account_info.jpg" width="250"/> | <img src="screenshots/deposit.jpg" width="250"/> |

---

## ✨ Features

* **🔐 Secure Authentication:** A dedicated PIN entry screen that restricts access to sensitive account data. PIN : 1234
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
2.  **ViewModel:** Manages the UI state using `StateFlow`. It handles business logic and exposes immutable state to the View.
3.  **View (Compose):** Stateless Composables that observe the ViewModel's state and trigger events back to the ViewModel.

### 🛠️ Tech Stack
* **Language:** [Kotlin]
* **UI Framework:** [Jetpack Compose] (Material Design 3)
* **Concurrency:** Coroutines & Flow

---

## ⚙️ Set Up & Local Installation

To set up this project locally on your machine, follow these steps.

### Prerequisites
Before you begin, ensure you have the following installed:
* **Android Studio:** Koala, Ladybug, or newer (Essential for Compose Previews).
* **Java Development Kit (JDK):** Version 17 or 21 (Required for AGP 8.0+).
* **Git:** To clone the repository.

### Installation Steps

1.  **Clone the Repository**
    Open your terminal or command prompt and run:
    ```bash
    git clone [https://github.com/your-username/bank-simulation-mvvm.git](https://github.com/your-username/bank-simulation-mvvm.git)
    cd bank-simulation-mvvm
    ```

2.  **Import Project**
    * Open Android Studio.
    * Select **File > Open...**
    * Navigate to the `bank-simulation-mvvm` folder you just cloned and click **OK**.

3.  **Sync Gradle**
    * Android Studio will automatically detect the `build.gradle.kts` files.
    * Allow the project to **Sync**. This downloads all necessary libraries (Compose, Coroutines, etc.).
    * *Note:* If you encounter a JDK error, go to `Settings > Build, Execution, Deployment > Build Tools > Gradle` and ensure the correct JDK is selected.

---

## 🚀 How to Run

Once the installation and sync are complete:

1.  **Select a Device:**
    * Create an Android Virtual Device (AVD) via Device Manager (Recommended: Pixel 7, API 33+).
    * OR connect a physical Android device via USB (Ensure USB Debugging is enabled).

2.  **Execute:**
    * Click the green **Run** button (▶) in the top toolbar or press `Shift + F10`.

3.  **Login:**
    * The app will launch to the **PIN Screen**.
    * **Default PIN:** `1234` (Update this in `MainViewModel.kt` if needed).
    * *Note: Since this is a simulation, data is reset when the app is killed.*

---

**Developed by [Lazaros]**
[🔗 GitHub](https://github.com/Elarios77)
