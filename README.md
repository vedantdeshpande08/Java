# Library Management System (CLI Edition)

A command-line **Library Management System** developed in Core Java for a Programming in Java course project. The system demonstrates object-oriented programming, layered architecture, custom exception handling, file-based data persistence, logging, and assertion-based testing.

The application runs completely through the terminal and does not require a GUI, database server, external libraries, or an internet connection.

---

## 1. Project Overview

The Library Management System is designed to manage the basic operations of a small library through a simple menu-driven command-line interface.

The system consists of four major functional modules:

1. **Member Management** — Register, list, update, deactivate, and delete members.
2. **Book / Inventory Management** — Add, list, search, update, and delete books while tracking total and available copies.
3. **Transaction Management** — Issue and return books, track overdue transactions, and calculate fines.
4. **Reports and Analytics** — Display library summary statistics and top-borrowed books.

All important data is stored in CSV files so that information is preserved between program executions.

Application activities are recorded in a log file with timestamps.

---

## 2. Project Objectives

The main objectives of this project are:

* To develop a practical Library Management System using Core Java.
* To apply object-oriented programming concepts in a real-world application.
* To implement CRUD operations for members and books.
* To manage book issuing and returning through transaction processing.
* To implement automatic fine calculation for overdue books.
* To demonstrate file handling and persistent data storage using CSV files.
* To apply exception handling and input validation.
* To organize the application using a modular layered architecture.
* To implement testing without relying on external testing frameworks.
* To provide useful library reports and borrowing statistics.

---

## 3. Functional Requirements

The system provides the following functional requirements:

### 3.1 Member Management

* Register new members.
* Display registered members.
* Update member information.
* Deactivate members.
* Delete members.
* Generate unique member IDs.

### 3.2 Book / Inventory Management

* Add new books.
* Display available books.
* Search for books.
* Update book information.
* Delete books.
* Track total copies.
* Track available copies.
* Generate unique book IDs.

### 3.3 Transaction Management

* Issue books to registered members.
* Return issued books.
* Generate transaction IDs.
* Track transaction status.
* Track overdue transactions.
* Calculate fines automatically for late returns.
* Update available book copies after issue and return.

### 3.4 Reports and Analytics

* Display library summary statistics.
* Display borrowing information.
* Identify the most-borrowed books.
* Provide basic information about library activity.

---

## 4. Non-Functional Requirements

### 4.1 Usability

The system provides a simple menu-driven command-line interface with clear options and input prompts.

### 4.2 Reliability

Library data is stored in CSV files so that information is preserved when the application is closed and restarted.

### 4.3 Maintainability

The application follows a layered architecture separating models, repositories, services, utilities, and the CLI.

### 4.4 Error Handling

Input validation and custom checked exceptions are used to handle invalid inputs, missing records, duplicate entries, and invalid library operations.

### 4.5 Performance

The application is lightweight and uses local CSV storage, making it suitable for managing data for a small library without requiring a database server.

### 4.6 Resource Efficiency

The system uses only standard JDK functionality and does not require external libraries, database servers, or internet connectivity.

### 4.7 Logging

Important application activities are recorded with timestamps in `logs/app.log`, providing basic monitoring and traceability.

---

## 5. Features

* Menu-driven command-line interface
* Input validation
* Object-oriented design using Core Java
* Layered architecture
* Member management
* Book and inventory management
* CRUD operations
* Book issuing and returning
* Automatic overdue fine calculation
* Overdue transaction tracking
* Library reports and statistics
* Custom checked exceptions
* CSV-based file persistence
* Automatic loading and saving of data
* Timestamp-based application logging
* 9 assertion-based tests
* No external libraries or database required
* Completely offline operation

### Fine Calculation

The default loan period is **14 days**.

A fine of **₹5 per overdue day** is applied when a book is returned after the allowed loan period.

These values can be changed in `TransactionService.java`.

---

## 6. Technologies Used

| Component            | Technology                         |
| -------------------- | ---------------------------------- |
| Programming Language | Java                               |
| Java Version         | JDK 17 or newer                    |
| Development          | VS Code / Any Java IDE             |
| Compilation          | `javac`                            |
| Execution            | `java`                             |
| Data Storage         | CSV files                          |
| Testing              | Hand-written assertion-based tests |
| Logging              | Custom Java logging utility        |
| Version Control      | Git                                |

The project does not require Maven, Gradle, JUnit, a database server, or any other external dependency.

---

## 7. Project Structure

```text
LibraryManagementSystem/
│
├── README.md
├── statement.md
├── DESIGN.md
├── .gitignore
│
├── data/
│   ├── books.csv
│   ├── members.csv
│   └── transactions.csv
│
├── logs/
│   └── app.log
│
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── library/
    │               ├── Main.java
    │               │
    │               ├── model/
    │               │   ├── Book.java
    │               │   ├── Member.java
    │               │   ├── Transaction.java
    │               │   └── TransactionStatus.java
    │               │
    │               ├── exception/
    │               │   ├── LibraryException.java
    │               │   ├── NotFoundException.java
    │               │   ├── DuplicateEntryException.java
    │               │   └── BusinessRuleException.java
    │               │
    │               ├── util/
    │               │   ├── AppLogger.java
    │               │   ├── IdGenerator.java
    │               │   └── InputValidator.java
    │               │
    │               ├── repository/
    │               │   ├── FileRepository.java
    │               │   ├── BookRepository.java
    │               │   ├── MemberRepository.java
    │               │   └── TransactionRepository.java
    │               │
    │               └── service/
    │                   ├── BookService.java
    │                   ├── MemberService.java
    │                   ├── TransactionService.java
    │                   └── ReportService.java
    │
    └── test/
        └── java/
            └── com/
                └── library/
                    └── SimpleTestRunner.java
```

---

## 8. System Architecture

The project follows a layered architecture:

```text
                 ┌─────────────────────┐
                 │     Main / CLI      │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │      Services       │
                 │                     │
                 │ BookService         │
                 │ MemberService       │
                 │ TransactionService  │
                 │ ReportService       │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │    Repositories     │
                 │                     │
                 │ BookRepository      │
                 │ MemberRepository    │
                 │ TransactionRepository│
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │    CSV Storage      │
                 │                     │
                 │ books.csv           │
                 │ members.csv         │
                 │ transactions.csv    │
                 └─────────────────────┘
```

### Main Layers

**Model Layer**
Contains the application's main entities such as `Book`, `Member`, and `Transaction`.

**Repository Layer**
Handles reading and writing application data to CSV files.

**Service Layer**
Contains the main business rules and operations of the library.

**CLI Layer**
Provides the menu-driven terminal interface through `Main.java`.

---

## 9. Prerequisites

Before running the project, make sure you have:

* **JDK 17 or newer**
* A terminal such as PowerShell, Command Prompt, Bash, or Terminal
* Git, if cloning the repository

Verify Java installation:

```bash
java -version
javac -version
```

Both commands should show JDK 17 or a newer version.

No database, Maven, Gradle, or internet connection is required.

---

## 10. Setup and Installation

### Step 1 — Get the Project

Clone the repository:

```bash
git clone <your-repository-url>
cd LibraryManagementSystem
```

If the project is provided as a ZIP file, extract it and open a terminal inside the extracted `LibraryManagementSystem` folder.

---

### Step 2 — Compile the Project

#### Linux / macOS

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
```

#### Windows PowerShell

```powershell
mkdir out
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName })
```

The compiled `.class` files will be placed inside the `out` directory.

---

### Step 3 — Run the Application

From the project root:

```bash
java -cp out com.library.Main
```

The application will display the main menu:

```text
===================================================
     LIBRARY MANAGEMENT SYSTEM (CLI Edition)
===================================================

1. Member Management
2. Book Management
3. Transaction Management (Issue / Return)
4. Reports
0. Exit

Choose an option:
```

The required `data/` and `logs/` files are created automatically when required by the application.

---

## 11. Usage Walkthrough

A typical workflow is:

### 1. Register a Member

Select:

```text
1 → 1
```

Enter:

* Name
* Email
* Phone number

The system generates a member ID such as:

```text
MB0001
```

### 2. Add a Book

Select:

```text
2 → 1
```

Enter:

* Title
* Author
* ISBN
* Category
* Number of copies

The system generates a book ID such as:

```text
BK0001
```

### 3. Issue a Book

Select:

```text
3 → 1
```

Enter the Book ID and Member ID.

Example:

```text
Book ID: BK0001
Member ID: MB0001
```

A transaction ID such as the following is generated:

```text
TX0001
```

### 4. Return a Book

Select:

```text
3 → 2
```

Enter the transaction ID:

```text
TX0001
```

The system processes the return and calculates a fine if the book is overdue.

### 5. View Reports

Select:

```text
4
```

The reporting module displays library statistics and borrowing information, including the most-borrowed books.

### 6. Exit

Select:

```text
0
```

Data changes are saved to the CSV files.

---

## 12. Data Persistence

The system uses CSV files instead of a database.

The main data files are:

```text
data/
├── books.csv
├── members.csv
└── transactions.csv
```

This approach keeps the project simple while demonstrating Java file handling and persistent storage.

Application logs are stored in:

```text
logs/app.log
```

The log records important application activities with timestamps.

---

## 13. Exception Handling

The project uses custom checked exceptions to handle application errors.

Examples include:

* `LibraryException`
* `NotFoundException`
* `DuplicateEntryException`
* `BusinessRuleException`

These exceptions provide meaningful error handling instead of relying only on generic runtime exceptions.

Examples of situations handled include:

* Searching for a non-existing book
* Searching for a non-existing member
* Duplicate entries
* Invalid library operations
* Business-rule violations
* Invalid input

---

## 14. Testing

The project contains a dependency-free assertion-based test runner.

No JUnit, Maven, or Gradle is required.

### Compile Main and Test Sources

#### Linux / macOS

```bash
javac -d out $(find src/main/java src/test/java -name "*.java")
```

#### Windows PowerShell

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java,src/test/java | ForEach-Object { $_.FullName })
```

### Run Tests

```bash
java -cp out com.library.SimpleTestRunner
```

Expected output:

```text
===================================
Tests passed: 9, failed: 0
===================================
```

The test runner uses an isolated test-data directory so that testing does not modify the application's normal library data.

---

## 15. Configuration

The main configurable values are located in:

```text
TransactionService.java
```

Default values:

```java
private static final int LOAN_PERIOD_DAYS = 14;
private static final double FINE_PER_DAY = 5.0;
```

This means:

* Loan period = 14 days
* Fine = ₹5 per overdue day

To change these rules, modify the constants and recompile the project.

---

## 16. Validation and Error Handling

The application validates user input before performing operations.

Examples include:

* Empty input validation
* Invalid numeric input
* Invalid IDs
* Duplicate records
* Non-existing records
* Invalid transaction operations
* Book availability checks
* Member validity checks

Meaningful error messages are displayed when an operation cannot be completed.

---

## 17. Troubleshooting

| Problem                                   | Solution                                                          |
| ----------------------------------------- | ----------------------------------------------------------------- |
| `javac: command not found`                | Install JDK 17+ and configure the Java PATH.                      |
| `java: command not found`                 | Make sure Java is installed and available in PATH.                |
| `error: release version 17 not supported` | Install JDK 17 or a newer JDK.                                    |
| Data appears incorrect or outdated        | Delete the `data/` folder and start the application again.        |
| Permission denied for `data/` or `logs/`  | Run the project from a directory where you have write permission. |
| Application does not start                | Recompile the source files and verify the classpath command.      |

---

## 18. Design Documentation

Additional project documentation is available in:

### `DESIGN.md`

Contains:

* System architecture diagram
* Workflow / process diagram
* Use Case Diagram
* Class Diagram
* Sequence Diagram
* System design information

### `statement.md`

Contains:

* Problem statement
* Project scope
* Target users
* High-level features

---

## 19. Learning Objectives

This project demonstrates practical use of several Java programming concepts:

* Classes and Objects
* Encapsulation
* Inheritance
* Polymorphism
* Abstraction
* Interfaces
* Collections
* Exception Handling
* File Handling
* Generics
* Java Streams
* Date and Time API
* Modular program structure
* Input validation
* Logging
* Testing
* Git-based version control

---

## 20. Future Enhancements

Possible future improvements include:

* GUI-based interface using JavaFX or Swing
* Database integration using JDBC
* Role-based access for librarians and members
* Book reservation system
* Email notifications
* Advanced analytics
* Improved search and filtering
* Exportable reports
* Authentication and user accounts

These features are outside the current CLI implementation.

---

## 21. Project Status

**Status:** Completed CLI-based Core Java project

**Application Type:** Command-Line Application

**Database:** Not required

**External Dependencies:** None

**Internet Required:** No

**Java Version:** JDK 17+

---

## 22. License

This project is developed for educational purposes as part of a Programming in Java course project.
