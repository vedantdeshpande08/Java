## Problem Statement

Small and mid-sized libraries (college department libraries, community
libraries, school libraries) often track their book inventory, member
records, and lending activity using manual registers or ad-hoc spreadsheets.
This leads to duplicate member entries, no visibility into which books are
overdue, no consistent way to calculate fines, and no historical record of
who borrowed what and when.

This project builds a **Library Management System (CLI Edition)** in Java
that digitizes these operations: managing members, managing the book
catalogue, and recording issue/return transactions with automatic fine
calculation — all runnable from a terminal, with data safely persisted to
disk between sessions.

## Scope of the Project

**In scope:**
- Member registration, lookup, update, deactivation, and deletion
- Book catalogue management: add, update, search, delete, track available
  vs. total copies
- Issuing a book to a member and recording its return, including automatic
  overdue-fine calculation
- Preventing invalid operations (issuing an unavailable book, issuing to an
  inactive member, duplicate member emails, malformed input)
- Basic reporting: total inventory, members, currently issued books,
  overdue transactions, total fines collected, and top-borrowed books
- Persistent storage via CSV files (no external database server required)
- Action logging for traceability

**Out of scope:**
- A graphical or web user interface (explicitly CLI-only, per assignment
  requirements)
- Multi-user concurrent access / networked client-server operation
- Payment gateway integration for fine collection (fines are calculated and
  recorded, not collected online)
- Book reservation / hold queues

## Target Users

- **Librarians / library staff** — day-to-day users who register members,
  manage the catalogue, and process issues/returns.
- **Library administrators** — use the reporting module to understand usage
  patterns (most-borrowed books, overdue counts, fine totals).
- **Course evaluators** — reviewing this project as a demonstration of core
  Java concepts: OOP design, collections, exception handling, file I/O, and
  modular architecture.

## High-Level Features

1. **Member Management module** — full CRUD over library members with email/
   phone validation and duplicate-email prevention.
2. **Book Management module** — full CRUD over the book catalogue with
   keyword search and copy-count tracking.
3. **Transaction Management module** — issue/return workflow enforcing
   business rules (availability, active-member checks) with automatic
   fine calculation for late returns.
4. **Reporting module** — aggregate statistics and top-borrowed-books
   analytics computed on demand from live data.
