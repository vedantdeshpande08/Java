# Library Management System (CLI Edition)

A command-line Library Management System developed using **Core Java**. The project demonstrates object-oriented programming, modular design, layered architecture, file handling, exception handling, input validation, logging, and testing.

The system is designed for a small library and provides separate modules for managing members, books, book transactions, and reports.

The project does not require a database server, external libraries, or an internet connection.

---

## 1. Project Overview

The **Library Management System (CLI Edition)** is a Java-based application that helps manage common library operations through a menu-driven command-line interface.

The system allows the user to:

* Register and manage library members
* Add and manage books
* Search and update book information
* Issue books to members
* Return issued books
* Calculate overdue fines
* Track transactions
* Generate library reports
* Store data permanently using CSV files
* Record important application activities in a log file

The project follows a modular structure using separate **model, repository, service, utility, exception, and CLI** components.

---

## 2. Problem Statement

Managing books, members, and borrowing transactions manually can lead to problems such as incorrect records, difficulty tracking available books, repeated data entry, and errors in calculating overdue fines.

The proposed Library Management System provides a simple command-line solution for managing these activities digitally. It maintains library records using CSV files and applies Java programming concepts to perform operations such as CRUD, validation, transaction processing, exception handling, and reporting.

---

## 3. Project Objectives

The main objectives of the project are:

1. To develop a functional Library Management System using Core Java.
2. To apply object-oriented programming concepts in a real-world application.
3. To implement separate modules for members, books, transactions, and reports.
4. To provide CRUD operations for library records.
5. To implement file-based data persistence using CSV files.
6. To validate user input and handle invalid operations using custom exceptions.
7. To calculate overdue fines automatically.
8. To generate useful library statistics and reports.
9. To demonstrate modular and maintainable Java programming.
10. To test important application operations using Java-based validation tests.

---

## 4. Scope of the Project

The project focuses on the basic operations required to manage a small library.

### Included in the scope

* Member registration and management
* Book and inventory management
* Book issue and return operations
* Overdue tracking and fine calculation
* Library reports
* CSV-based data storage
* Input validation
* Exception handling
* Application logging
* Basic automated validation testing

### Outside the current scope

* Graphical User Interface
* Online library access
* Cloud database
* User authentication
* Online payment processing
* Multi-library synchronization

These features can be considered for future development.

---

## 5. Target Users

The system is intended for:

* Small library administrators
* Librarians
* Educational institutions
* Students learning Java programming
* Small-scale library environments requiring a simple offline system

---

## 6. Functional Requirements

The system contains four major functional modules.

### 6.1 Member Management

The Member Management module provides:

* Register a new member
* Display members
* Search members
* Update member information
* Deactivate members
* Delete members
* Validate member details
* Generate unique member IDs

**Input:**

* Member name
* Email
* Phone number

**Output:**

* Member ID
* Member details
* Success/error messages

---

### 6.2 Book / Inventory Management

The Book Management module provides:

* Add books
* Display books
* Search books
* Update book information
* Delete books
* Track total copies
* Track available copies
* Generate unique book IDs

**Input:**

* Book title
* Author
* ISBN
* Category
* Number of copies

**Output:**

* Book ID
* Book details
* Availability information
* Success/error messages

---

### 6.3 Transaction Management

The Transaction Management module handles:

* Issue a book
* Return a book
* Track active transactions
* Track overdue books
* Calculate overdue fines
* Update book availability
* Validate member and book IDs

The current default borrowing rule is:

* Loan period: **14 days**
* Fine: **₹5 per overdue day**

These values can be changed in `TransactionService.java`.

**Input:**

* Book ID
* Member ID
* Transaction ID for return operations

**Output:**

* Transaction ID
* Issue/return status
* Due date
* Fine amount when applicable

---

### 6.4 Reports and Analytics

The Reports module provides:

* Total number of books
* Total number of members
* Available book information
* Active transaction information
* Most-borrowed book information
* Library summary statistics

**Input:**

* Report selection from the CLI menu

**Output:**

* Library statistics
* Transaction summary
* Most-borrowed-book information

---

## 7. System Features

* Menu-driven command-line interface
* Member CRUD operations
* Book CRUD operations
* Book issue and return
* Automatic overdue fine calculation
* CSV-based persistence
* Input validation
* Custom exception handling
* Logging
* Library reports
* Unique ID generation
* Dependency-free Java testing
* Modular package structure

---

## 8. Non-Functional Requirements

### 8.1 Usability

The application provides a menu-driven CLI so that users can select operations using simple numbered options.

### 8.2 Reliability

Library records are stored in CSV files so that data remains available after the application is closed and restarted.

### 8.3 Maintainability

The application is divided into separate packages such as `model`, `repository`, `service`, `util`, and `exception`, making the code easier to understand and modify.

### 8.4 Error Handling

The system validates user input and uses custom exceptions such as:

* `NotFoundException`
* `DuplicateEntryException`
* `BusinessRuleException`

This prevents invalid operations from being processed silently.

### 8.5 Performance

The system is designed for small-scale library operations and uses local file storage, avoiding network or database communication overhead.

### 8.6 Resource Efficiency

The application uses only standard Java libraries and local CSV files. No external server or database is required.

### 8.7 Logging

Important application activities and errors are recorded in:

```text
logs/app.log
```

---

## 9. Technologies and Tools Used

| Technology / Tool                    | Purpose                     |
| ------------------------------------ | --------------------------- |
| Java                                 | Main programming language   |
| JDK 17+                              | Compilation and execution   |
| `javac`                              | Compiling Java source files |
| `java`                               | Running the application     |
| CSV Files                            | Data persistence            |
| Git                                  | Version control             |
| Terminal / PowerShell                | Application execution       |
| Java Assertions / Custom Test Runner | Testing                     |

### External Dependencies

The project does **not** require:

* Maven
* Gradle
* JUnit
* MySQL
* Any database server
* External Java libraries
* Internet connection

---

## 10. Java Concepts Demonstrated

This project is specifically designed for a **Programming in Java** course and demonstrates:

* Classes and objects
* Encapsulation
* Constructors
* Inheritance
* Polymorphism
* Interfaces
* Generics
* Collections
* Exception handling
* Custom exceptions
* File handling
* CSV processing
* Date and time API
* String handling
* Input validation
* Modular programming
* Packages
* Logging
* Basic testing

---

## 11. System Architecture

The application follows a layered architecture.

```text
                 +----------------------+
                 |      Main / CLI      |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 |       Services       |
                 |----------------------|
                 | MemberService        |
                 | BookService          |
                 | TransactionService  |
                 | ReportService        |
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 |     Repositories     |
                 |----------------------|
                 | MemberRepository     |
                 | BookRepository       |
                 | TransactionRepository|
                 +----------+-----------+
                            |
                            v
                 +----------------------+
                 |    CSV File Storage  |
                 +----------------------+

       Supporting Components:
       Model + Exceptions + Utilities + Logger
```

### Layer Responsibilities

**CLI Layer**

Handles user interaction and menu selection.

**Service Layer**

Contains the main business logic.

**Repository Layer**

Handles reading and writing persistent data.

**Model Layer**

Represents entities such as books, members, and transactions.

**Exception Layer**

Handles application-specific errors.

**Utility Layer**

Provides validation, ID generation, and logging functionality.

---

## 12. Project Structure

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

## 13. Prerequisites

Before running the project, install:

* **JDK 17 or newer**
* Terminal, Command Prompt, or PowerShell
* Git if cloning the repository

Verify Java installation:

```bash
java -version
javac -version
```

Both commands should show JDK 17 or a newer version.

---

## 14. Installation and Setup

### Step 1 — Clone the Repository

```bash
git clone <your-repository-url>
cd LibraryManagementSystem
```

If the project is received as a ZIP file, extract it and open the terminal inside the project folder.

---

### Step 2 — Compile the Project

#### Linux / macOS

```bash
mkdir -p out
javac -d out $(find src/main/java -name "*.java")
```

#### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force out
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName })
```

#### Windows Command Prompt

```cmd
mkdir out
dir /s /b src\main\java\*.java > sources.txt
javac -d out @sources.txt
del sources.txt
```

---

## 15. Running the Application

After successful compilation:

```bash
java -cp out com.library.Main
```

The application displays a menu similar to:

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

---

## 16. Application Workflow

```text
Start
  |
  v
Main Menu
  |
  +----> Member Management
  |          |
  |          +--> Add / View / Update / Delete
  |
  +----> Book Management
  |          |
  |          +--> Add / View / Search / Update / Delete
  |
  +----> Transaction Management
  |          |
  |          +--> Issue Book
  |          |
  |          +--> Return Book
  |          |
  |          +--> Calculate Fine
  |
  +----> Reports
  |          |
  |          +--> Library Statistics
  |          +--> Borrowing Information
  |
  v
Save Data
  |
  v
Exit
```

---

## 17. Usage Walkthrough

A typical session can follow these steps:

### Step 1 — Register a Member

Select:

```text
1 → Member Management
```

Enter the requested member details.

A unique Member ID is generated.

Example:

```text
MB0001
```

### Step 2 — Add a Book

Select:

```text
2 → Book Management
```

Enter:

* Title
* Author
* ISBN
* Category
* Number of copies

A unique Book ID is generated.

Example:

```text
BK0001
```

### Step 3 — Issue a Book

Select:

```text
3 → Transaction Management
```

Enter the Book ID and Member ID.

The system creates a transaction and updates the available book count.

Example:

```text
Transaction ID: TX0001
```

### Step 4 — Return the Book

Select the return option and provide the transaction ID.

The system updates the transaction status and book availability.

If the book is overdue, the system calculates the fine automatically.

### Step 5 — View Reports

Select:

```text
4 → Reports
```

The system displays library statistics and borrowing information.

---

## 18. Data Storage

The project uses CSV files for persistent storage.

### `books.csv`

Stores book information such as:

```text
Book ID
Title
Author
ISBN
Category
Total Copies
Available Copies
```

### `members.csv`

Stores member information such as:

```text
Member ID
Name
Email
Phone
Status
```

### `transactions.csv`

Stores transaction information such as:

```text
Transaction ID
Book ID
Member ID
Issue Date
Due Date
Return Date
Status
Fine
```

The CSV files are stored inside:

```text
data/
```

---

## 19. Data Persistence

The system loads existing CSV data when the application starts and saves changes to the files during operations.

Therefore, records remain available after restarting the application.

To reset the application data, the existing CSV files inside the `data/` directory can be removed before starting a fresh session.

---

## 20. Exception Handling

The application uses custom exceptions to handle invalid operations.

Examples include:

### `NotFoundException`

Used when a requested member, book, or transaction does not exist.

### `DuplicateEntryException`

Used when a duplicate record is detected.

### `BusinessRuleException`

Used when an operation violates a library business rule, such as trying to issue an unavailable book.

This approach keeps error handling organized and improves code maintainability.

---

## 21. Input Validation

User inputs are validated before processing.

Examples include:

* Empty input checking
* ID validation
* Numeric input validation
* Email validation
* Phone number validation
* Book availability validation
* Member existence validation

Invalid input results in an appropriate error message instead of silently processing incorrect data.

---

## 22. Logging

Application activities are recorded in:

```text
logs/app.log
```

Logging can be used to track important operations and errors during application execution.

The logging implementation is handled by:

```text
AppLogger.java
```

---

## 23. Testing

The project includes a dependency-free Java test runner.

No JUnit or external testing framework is required.

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

### Expected Result

```text
===================================
Tests passed: 9, failed: 0
===================================
```

The test runner is designed to use an isolated test-data directory so that testing does not modify the application's normal library data.

---

## 24. Configuration

The default borrowing rules are defined in:

```text
TransactionService.java
```

Current values:

```java
private static final int LOAN_PERIOD_DAYS = 14;
private static final double FINE_PER_DAY = 5.0;
```

This means:

* Loan period = 14 days
* Fine = ₹5 per overdue day

These values can be modified according to project requirements and the project can then be recompiled.

---

## 25. Troubleshooting

| Problem                    | Possible Solution                                                |
| -------------------------- | ---------------------------------------------------------------- |
| `java` command not found   | Install JDK 17+ and add Java to PATH                             |
| `javac` command not found  | Install a full JDK instead of only a JRE                         |
| Compilation errors         | Verify that JDK 17 or newer is installed                         |
| Data is incorrect or stale | Reset the CSV files in the `data/` directory                     |
| Permission denied          | Run the project from a directory where you have write permission |
| Application does not start | Recompile all source files and run `com.library.Main`            |
| Tests do not run           | Compile both `src/main/java` and `src/test/java`                 |

---

## 26. Design Documentation

Additional design information is provided in:

```text
DESIGN.md
```

The design document should contain:

* Problem Statement
* Project Objectives
* Functional Requirements
* Non-Functional Requirements
* System Architecture Diagram
* Process / Workflow Diagram
* Use Case Diagram
* Class Diagram
* Sequence Diagram
* Storage Design / CSV Schema

The project does not use a relational database, so an ER diagram is not required. The CSV storage schema is used instead.

---

## 27. Learning Outcomes

After completing this project, the developer gains practical experience in:

* Designing Java applications
* Applying OOP principles
* Creating modular Java packages
* Working with collections
* Implementing CRUD operations
* Reading and writing files
* Handling exceptions
* Validating user input
* Using Java date/time functionality
* Designing service and repository layers
* Writing basic tests
* Using Git for version control
* Documenting a software project

---

## 28. Future Enhancements

Possible future improvements include:

* Graphical User Interface
* Database integration
* Login and authentication
* Role-based access
* Advanced search and filtering
* Email notifications for overdue books
* Book reservation system
* Exporting reports
* Barcode or QR-code integration
* Web-based version
* Cloud-based storage

---

## 29. Project Status

**Status:** Completed Java CLI project

**Application Type:** Command-Line Application

**Language:** Java

**Storage:** CSV files

**External Dependencies:** None

---

## 30. Version Control

Git is used for version control.

Recommended repository structure:

```text
main
├── README.md
├── statement.md
├── DESIGN.md
├── src/
├── data/
└── logs/
```

Meaningful commits should be used during development, for example:

```text
Initial project setup
Added member management
Added book management
Added transaction module
Added fine calculation
Added report module
Added exception handling
Added file persistence
Added testing
Updated documentation
```

---

## 31. License

This project is developed for educational purposes as part of the **Programming in Java** course project.

---
