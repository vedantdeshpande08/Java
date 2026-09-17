# Library Management System (CLI Edition)

A command-line **Library Management System** written in Core Java as part of a Programmation in Java project. This program showcases object-oriented programming concepts in the layered architecture, the usage of custom exceptions, the use of file-based data persistence, and also logging and assertion-based testing.

The system runs entirely in the command line without the need for a GUI, database server, libraries, or internet access.

## 1. Project Overview

I have been developing the simple menu-driven command line interface-based library management system for a small library.

The system consists of four modules, namely,

1. Member Management

2. Books/Inventory Management

3. Transaction Management

4. Reports

The Member Management module facilitates the addition of new members, viewing all the existing members, updating the member’s information, deactivating and deleting members.

The second module allows adding books, viewing all the books, searching for a particular book, modifying the book’s information, deleting the book, and keeping track of the copies of the book in inventory. This module also consists of features to record the number of copies issued and available for each book.

The Transaction Management module is used to record the issuance of books, receive the returned books, keep a record of overdue books and calculate the fines.

The Reports module helps generate the general statics of the library’s performance, borrowing patterns of the books and members, and the most and least borrowed books.

The library management system stores the data in CSV files to retain the information even after the termination of the application. Additionally, every event that occurs during the application’s functioning is recorded in a log file with timestamps.

---

## 2. Features

A menu-driven command line interface-based navigation.

Validation of user input.

Core Java based object-oriented programming approach.

A layered approach.

Member Management: adding, viewing, updating, deleting, and deactivating members.

Books/Inventory Management: adding, viewing, searching, modifying, deleting, and inventory tracking of books.

Transaction Management: issuing books, receiving returned books, recording overdue books, and calculating fines.

Generation of reports about the general statics, borrowing patterns, and most and least borrowed books.

Customized exception handling;

Data storage in CSV files;

Loading and saving data upon initiation and termination of the application; logger module with timestamps;

Testing without using a JUnit framework;

No dependency on external libraries or databases;

Implementation of an offline application;

Fine calculation based on the number of overdue days;

The automatic calculation of the fine upon the return of the overdue book;

Record of all the transactions;

No manual calculation of fines;

I have implemented the logic that permanently stores all the information in CSV files.


## 3. Technologies Used

Component Technology
----|----
Programming Language: Java
Java Version: JDK 17+
Development: VS Code / Any Java IDE
Compilation: javac
Execution: java
Data Storage: CSV files
Testing: Hand-written assertion-based tests
Logging: Custom Java logging utility
Version Control: Git

None of Maven, Gradle, JUnit, or a database server are used.
---

4. Project Structure

```text
LibraryManagementSystem/
│
├── README.md
├── statement.md
├── DESIGN.md
├── .gitignore
│
├── data/
│  ├── books.csv
│  ├── members.csv
│  └── transactions.csv
│
├── logs/
│  └── app.log
│
└── src/
├── main/
│  └── java/
│    └── com/
│      └── library/
│        ├── Main.java
│        │
│        ├── model/
│        │  ├── Book.java
│        │  ├── Member.java
│        │  ├── Transaction.java
│        │  └── TransactionStatus.java
│        │
│        ├── exception/
│        │  ├── LibraryException.java
│        │  ├── NotFoundException.java
│        │  ├── DuplicateEntryException.java
│        │  └── BusinessRuleException.java
│        │
│        ├── util/
│        │  ├── AppLogger.java
│        │  ├── IdGenerator.java
│        │  └── InputValidator.java
│        │
│        ├── repository/
│        │  ├── FileRepository.java
│        │  ├── BookRepository.java
│        │  ├── MemberRepository.java
│        │  └── TransactionRepository.java
│        │
│        └── service/
│          ├── BookService.java
│          ├── MemberService.java
│          ├── TransactionService.java
│          └── ReportService.java
│
└── test/
└── java/
└── com/
└── library/
└── SimpleTestRunner.java
```


## 5. Architecture

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

Model Layer

Which holds the basic entities used in the application like the `Book`, `Member` and `Transaction`.

Repository Layer

That is used for reading and writing the application data into CSV files.

Service Layer

Where the main business logic and processes behind the system are held.

CLI Layer

Used for the menu interface, the CLI or the `Main.java` file.

---

## 6. Prerequisites

Before we install the application, here are some tools we need to make sure that are installed on the computer:

JDK 17 or higher versions

A terminal emulator like the PowerShell, CMD, Bash or Terminal

A GitClone tool in case we want to clone the project from the repository.

We can check the Java Version by using the command below;

```bash

java -version

javac -version

```

The two commands above should return with the versions that are higher than JDK 17.

There is no need for databases, maven, gradle or the internet to run the project.

---

## 7. Setup and Installation

### Step 1 — Get the Project

We will need to git clone the project onto our computer by running the command below;

```bash

git clone

cd LibraryManagementSystem

```

In case of a zip folder, we will have to extract the file and open the terminal in the now created `LibraryManagementSystem` folder.

### Step 2 — Compiling The Project

Run the commands below;

#### Linux / macOS

```bash

mkdir -p out

javac -d out $(find src/main/java -name ".java")

```

#### Windows PowerShell

```powershell

mkdir out

javac -d out (Get-ChildItem -Recurse -Filter .java src/main/java | ForEach-Object { $_.FullName })

```

The compiled `.class` files are found in the `out` folder.

### Step 3 — Running The Application

To run the application, type the command below in the terminal:

```bash

java -cp out com.library.Main

```

Then we will be greeted by the menu CLI:

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

## 8. Basic Usage

A typical workflow is:

### 1. Register a Member

Select:

```text
1 → 1
```

Enter the member's:

* Name
* Email
* Phone number

The system generates a member ID such as:

```text
MB0001
```

---

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

---

### 3. Issue a Book

Select:

```text
3 → 1
```

Enter the required:

* Book ID
* Member ID

For example:

```text
Book ID: BK0001
Member ID: MB0001
```

A transaction ID such as the following is generated:

```text
TX0001
```

---

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

---

### 5. View Reports

Select:

```text
4
```

The reporting module displays library statistics and borrowing information, including the most-borrowed books.

---

### 6. Exit

Select:

```text
0
```

Data changes are saved to the CSV files.

When the application is started again, previously stored data can be loaded from the files.

---

## 9. Data Persistence

The system uses CSV files instead of a database.

The main data files are:

```text
data/
├── books.csv
├── members.csv
└── transactions.csv
```

This approach keeps the project simple and demonstrates file handling using standard Java functionality.

Application logs are stored in:

```text
logs/app.log
```

The log records important application activities with timestamps.

---

## 10. Exception Handling

The project uses custom checked exceptions to handle application errors.

Examples include:

* `NotFoundException`
* `DuplicateEntryException`
* `BusinessRuleException`
* `LibraryException`

These exceptions are used to provide meaningful error handling instead of relying only on generic runtime exceptions.

Examples of situations handled include:

* Searching for a non-existing book
* Searching for a non-existing member
* Duplicate entries
* Invalid library operations
* Business-rule violations

---

## 11. Testing

The project contains a dependency-free test runner.

No JUnit, Maven, or Gradle is required.

Compile both the main and test source files:

### Linux / macOS

```bash
javac -d out $(find src/main/java src/test/java -name "*.java")
```

### Windows PowerShell

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java,src/test/java | ForEach-Object { $_.FullName })
```

Run the test runner:

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

## 12. Configuration

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

## 13. Troubleshooting

| Problem                                   | Solution                                                          |
| ----------------------------------------- | ----------------------------------------------------------------- |
| `javac: command not found`                | Install JDK 17+ and configure the Java PATH.                      |
| `java: command not found`                 | Make sure Java is installed and available in PATH.                |
| `error: release version 17 not supported` | Install JDK 17 or a newer JDK.                                    |
| Data appears incorrect or outdated        | Delete the `data/` folder and start the application again.        |
| Permission denied for `data/` or `logs/`  | Run the project from a directory where you have write permission. |
| Application does not start                | Recompile the source files and verify the classpath command.      |

---

## 14. Design Documentation

Additional project documentation is available in:

### `DESIGN.md`

Contains:

* System architecture
* Workflow diagram
* Use case diagram
* Class diagram
* Sequence diagram

### `statement.md`

Contains:

* Problem statement
* Project scope
* Target users
* Project requirements

---

## 15. Learning Objectives

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

## 16. Future Enhancements

Possible future improvements include:

* GUI-based interface using JavaFX or Swing
* Database integration using JDBC
* Role-based access for librarians and members
* Book reservation system
* Email notifications
* Advanced analytics
* Search and filtering improvements
* Exportable reports
* Authentication and user accounts

These features are outside the current CLI implementation.

---

## 17. Project Status

**Status:** Completed CLI-based Core Java project

**Application Type:** Command-Line Application

**Database:** Not required

**External Dependencies:** None

**Internet Required:** No

**Java Version:** JDK 17+

---

## 18. License

This project is developed for educational purposes as part of a Programming in Java course project.
