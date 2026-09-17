# Library Management System (CLI Edition)

A command-line Library Management System written in core Java. It demonstrates
object-oriented design, layered architecture (model / repository / service / CLI),
custom exception handling, file-based persistence, and unit testing — all using
nothing but the standard JDK (no external libraries, no database server, no
internet connection required).

> Built for a "Programming in Java" course project. Fully executable from the
> terminal — no GUI required.

---

## 1. Overview

The system manages a small library's day-to-day operations through three core
modules, plus a reporting module:

1. **Member Management** — register, list, update, deactivate, delete members.
2. **Book / Inventory Management** — add, list, search, update, delete books;
   track total vs. available copies.
3. **Transaction Management** — issue a book to a member, return a book,
   automatic fine calculation for late returns, overdue tracking.
4. **Reports** — library summary statistics and top-borrowed-books analytics.

Data is persisted to plain CSV files under `data/` so nothing is lost between
runs, and every meaningful action is logged with a timestamp to `logs/app.log`.

## 2. Features

- Menu-driven CLI with input validation at every step
- Layered architecture: `model` → `repository` → `service` → `Main` (CLI)
- Custom checked exceptions (`NotFoundException`, `DuplicateEntryException`,
  `BusinessRuleException`) instead of generic `RuntimeException`s
- File-based persistence (CSV) with automatic load-on-start / save-on-write
- Automatic fine calculation for overdue returns (₹5/day after a 14-day loan period)
- Simple, dependency-free logging utility
- 8 self-contained unit/validation checks (no JUnit required — see Testing below)

## 3. Technologies Used

| Item              | Choice                                   |
|-------------------|-------------------------------------------|
| Language           | Java (JDK 17+, tested on JDK 21)          |
| Build              | Plain `javac` / `java` — no Maven/Gradle required |
| Persistence        | Flat CSV files (`data/*.csv`)             |
| Testing            | Hand-written assertion-based test runner  |
| Version control    | Git                                        |

No external dependencies are required, so the project builds and runs
completely offline.

## 4. Project Structure

```
LibraryManagementSystem/
├── README.md
├── statement.md
├── DESIGN.md
├── .gitignore
├── data/                        # CSV data files (created automatically)
├── logs/                        # app.log (created automatically)
└── src/
    ├── main/java/com/library/
    │   ├── Main.java                     # CLI entry point
    │   ├── model/                        # Book, Member, Transaction, TransactionStatus
    │   ├── exception/                     # LibraryException and subtypes
    │   ├── util/                         # AppLogger, IdGenerator, InputValidator
    │   ├── repository/                   # FileRepository<T> + 3 concrete repositories
    │   └── service/                      # BookService, MemberService, TransactionService, ReportService
    └── test/java/com/library/
        └── SimpleTestRunner.java         # Dependency-free tests
```

## 5. Prerequisites

- **JDK 17 or newer** installed and on your `PATH`
  (verify with `java -version` and `javac -version`)
- A terminal (bash / PowerShell / cmd all work)
- Git (only needed if you're cloning the repository)

No other software, database, or internet connection is required.

## 6. Setup & Installation

### Step 1 — Get the code

```bash
git clone <your-repository-url>
cd LibraryManagementSystem
```

(If you received this as a zip file instead, just extract it and `cd` into
the extracted `LibraryManagementSystem` folder.)

### Step 2 — Compile the project

From the project root:

```bash
# Linux / macOS
mkdir -p out
javac -d out $(find src/main/java -name "*.java")

# Windows (PowerShell)
mkdir out
javac -d out (Get-ChildItem -Recurse -Filter *.java src/main/java | ForEach-Object { $_.FullName })
```

This compiles all source files into the `out/` directory.

### Step 3 — Run the application

```bash
java -cp out com.library.Main
```

You should see the main menu:

```
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

Data files (`data/books.csv`, `data/members.csv`, `data/transactions.csv`)
and the log file (`logs/app.log`) are created automatically on first run —
no manual configuration needed.

## 7. Usage Walkthrough

A typical first session:

1. Choose **1 → 1** to register a member (name, email, phone).
2. Choose **2 → 1** to add a book (title, author, ISBN, category, copies).
3. Choose **3 → 1** to issue that book to the member (enter the Book ID and
   Member ID shown when they were created, e.g. `BK0001`, `MB0001`).
4. Choose **3 → 2** and enter the transaction ID (e.g. `TX0001`) to return it.
   If returned after the 14-day loan period, a fine is calculated automatically.
5. Choose **4** to see summary statistics and the most-borrowed books.
6. Choose **0** to exit. All data is already saved to disk after every change.

Run the app again later — everything you added is still there.

## 8. Running the Tests

Tests are plain Java (no JUnit / Maven needed), so they compile and run with
the same JDK:

```bash
# Compile main + test sources together
javac -d out $(find src/main/java src/test/java -name "*.java")

# Run the test runner
java -cp out com.library.SimpleTestRunner
```

Expected output ends with something like:

```
===================================
Tests passed: 8, failed: 0
===================================
```

The test runner uses its own isolated `test-data/` folder (auto-created and
cleaned up) so it never touches your real `data/` files.

## 9. Screenshots

This is a terminal-only application (no GUI), so "screenshots" here are
sample CLI output from a real session.

**Main menu:**
```
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

**Adding a book and issuing it:**
```
Choose an option: 2

-- Book Management --
1. Add book
...
Choose an option: 1
Title: Clean Code
Author: Robert C. Martin
ISBN: 9780132350884
Category: Software Engineering
Number of copies: 3
Added: [BK0001] "Clean Code" by Robert C. Martin | ISBN:9780132350884 | Category:Software Engineering | Available:3/3

Choose an option: 3

-- Transaction Management (Issue / Return) --
1. Issue book
...
Choose an option: 1
Book ID: BK0001
Member ID: MB0001
Issued: [TX0001] Book:BK0001 Member:MB0001 Issued:2026-09-17 Due:2026-10-01 Returned:- Fine:Rs.0.00 Status:ISSUED
```

**Reports:**
```
Choose an option: 4

-- Reports --
---- Library Summary Report ----
Total titles          : 1
Total copies           : 3
Available copies       : 2
Registered members     : 1
Books currently issued : 1
Overdue transactions   : 0
Total fines collected  : Rs.0.00

Top borrowed books:
  BK0001 -> 1 issue(s)
```

**Test run:**
```
$ java -cp out com.library.SimpleTestRunner
[PASS] addBook + getBook round-trip
[PASS] duplicate email rejected
[PASS] invalid email rejected
[PASS] book unavailable after issue
[PASS] book available after return
[PASS] no fine for on-time return
[PASS] issue blocked when no copies available
[PASS] issue blocked for inactive member

===================================
Tests passed: 8, failed: 0
===================================
```

## 10. Configuration

No external configuration file is required. The only "configuration" values
are constants at the top of `TransactionService.java`:

```java
private static final int LOAN_PERIOD_DAYS = 14;  // loan period, in days
private static final double FINE_PER_DAY = 5.0;  // fine per overdue day (₹)
```

Change these and recompile if you want different loan/fine rules.

## 11. Troubleshooting

| Problem | Fix |
|---|---|
| `javac: command not found` | Install a full JDK (not just a JRE) and ensure it's on your `PATH`. |
| `error: release version 17 not supported` | You're on an older JDK. Install JDK 17+ (project uses `switch` arrow syntax and `Stream.toList()`, both Java 14+/16+ features). |
| Data looks stale / wrong | Delete the `data/` folder to reset the library to empty and start fresh. |
| Permission denied writing `logs/` or `data/` | Run the commands from a directory you have write access to. |

## 12. Design Documentation

See [`DESIGN.md`](DESIGN.md) for the system architecture diagram, workflow
diagram, use case diagram, class diagram, and sequence diagram.
See [`statement.md`](statement.md) for the problem statement, scope, and
target users.

