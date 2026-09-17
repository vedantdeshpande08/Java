package com.library;

import com.library.exception.LibraryException;
import com.library.model.Book;
import com.library.model.Member;
import com.library.model.Transaction;
import com.library.repository.BookRepository;
import com.library.repository.MemberRepository;
import com.library.repository.TransactionRepository;
import com.library.service.BookService;
import com.library.service.MemberService;
import com.library.service.ReportService;
import com.library.service.TransactionService;
import com.library.util.AppLogger;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Command-line entry point for the Library Management System.
 * Presents a menu-driven interface over the three functional modules:
 * Member Management, Book Management, and Transaction Management,
 * plus a Reports module for analytics.
 */
public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static final BookRepository bookRepository = new BookRepository("data/books.csv");
    private static final MemberRepository memberRepository = new MemberRepository("data/members.csv");
    private static final TransactionRepository transactionRepository =
            new TransactionRepository("data/transactions.csv");

    private static final BookService bookService = new BookService(bookRepository);
    private static final MemberService memberService = new MemberService(memberRepository);
    private static final TransactionService transactionService =
            new TransactionService(transactionRepository, bookService, memberService);
    private static final ReportService reportService =
            new ReportService(bookService, transactionService, memberService);

    public static void main(String[] args) {
        AppLogger.info("Application started");
        System.out.println("===================================================");
        System.out.println("     LIBRARY MANAGEMENT SYSTEM (CLI Edition)");
        System.out.println("===================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            String choice = SCANNER.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> memberMenu();
                    case "2" -> bookMenu();
                    case "3" -> transactionMenu();
                    case "4" -> reportsMenu();
                    case "0" -> {
                        running = false;
                        System.out.println("Goodbye!");
                        AppLogger.info("Application exited normally");
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
                AppLogger.warn("Handled error: " + e.getMessage());
            } catch (Exception e) {
                // Last-resort catch so an unexpected error never crashes the CLI session.
                System.out.println("Unexpected error: " + e.getMessage());
                AppLogger.error("Unexpected error: " + e);
            }
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("1. Member Management");
        System.out.println("2. Book Management");
        System.out.println("3. Transaction Management (Issue / Return)");
        System.out.println("4. Reports");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    // ---------------------- Member Management ----------------------

    private static void memberMenu() throws LibraryException {
        System.out.println("\n-- Member Management --");
        System.out.println("1. Register member");
        System.out.println("2. List members");
        System.out.println("3. Update member");
        System.out.println("4. Deactivate member");
        System.out.println("5. Delete member");
        System.out.print("Choose an option: ");
        String choice = SCANNER.nextLine().trim();

        switch (choice) {
            case "1" -> {
                System.out.print("Name: ");
                String name = SCANNER.nextLine().trim();
                System.out.print("Email: ");
                String email = SCANNER.nextLine().trim();
                System.out.print("Phone: ");
                String phone = SCANNER.nextLine().trim();
                Member m = memberService.registerMember(name, email, phone);
                System.out.println("Registered: " + m);
            }
            case "2" -> {
                List<Member> members = memberService.listMembers();
                if (members.isEmpty()) System.out.println("No members yet.");
                members.forEach(System.out::println);
            }
            case "3" -> {
                System.out.print("Member ID: ");
                String id = SCANNER.nextLine().trim();
                System.out.print("New name: ");
                String name = SCANNER.nextLine().trim();
                System.out.print("New email: ");
                String email = SCANNER.nextLine().trim();
                System.out.print("New phone: ");
                String phone = SCANNER.nextLine().trim();
                Member m = memberService.updateMember(id, name, email, phone);
                System.out.println("Updated: " + m);
            }
            case "4" -> {
                System.out.print("Member ID: ");
                String id = SCANNER.nextLine().trim();
                memberService.deactivateMember(id);
                System.out.println("Member deactivated.");
            }
            case "5" -> {
                System.out.print("Member ID: ");
                String id = SCANNER.nextLine().trim();
                memberService.deleteMember(id);
                System.out.println("Member deleted.");
            }
            default -> System.out.println("Invalid option.");
        }
    }

    // ---------------------- Book Management ----------------------

    private static void bookMenu() throws LibraryException {
        System.out.println("\n-- Book Management --");
        System.out.println("1. Add book");
        System.out.println("2. List books");
        System.out.println("3. Search books (title/author)");
        System.out.println("4. Update book");
        System.out.println("5. Add copies");
        System.out.println("6. Delete book");
        System.out.print("Choose an option: ");
        String choice = SCANNER.nextLine().trim();

        switch (choice) {
            case "1" -> {
                System.out.print("Title: ");
                String title = SCANNER.nextLine().trim();
                System.out.print("Author: ");
                String author = SCANNER.nextLine().trim();
                System.out.print("ISBN: ");
                String isbn = SCANNER.nextLine().trim();
                System.out.print("Category: ");
                String category = SCANNER.nextLine().trim();
                System.out.print("Number of copies: ");
                int copies = com.library.util.InputValidator.parsePositiveInt(SCANNER.nextLine(), "Copies");
                Book b = bookService.addBook(title, author, isbn, category, copies);
                System.out.println("Added: " + b);
            }
            case "2" -> {
                List<Book> books = bookService.listBooks();
                if (books.isEmpty()) System.out.println("No books yet.");
                books.forEach(System.out::println);
            }
            case "3" -> {
                System.out.print("Search keyword: ");
                String keyword = SCANNER.nextLine().trim();
                List<Book> results = bookService.searchByTitleOrAuthor(keyword);
                if (results.isEmpty()) System.out.println("No matches.");
                results.forEach(System.out::println);
            }
            case "4" -> {
                System.out.print("Book ID: ");
                String id = SCANNER.nextLine().trim();
                System.out.print("New title: ");
                String title = SCANNER.nextLine().trim();
                System.out.print("New author: ");
                String author = SCANNER.nextLine().trim();
                System.out.print("New ISBN: ");
                String isbn = SCANNER.nextLine().trim();
                System.out.print("New category: ");
                String category = SCANNER.nextLine().trim();
                Book b = bookService.updateBook(id, title, author, isbn, category);
                System.out.println("Updated: " + b);
            }
            case "5" -> {
                System.out.print("Book ID: ");
                String id = SCANNER.nextLine().trim();
                System.out.print("Extra copies: ");
                int extra = com.library.util.InputValidator.parsePositiveInt(SCANNER.nextLine(), "Copies");
                bookService.addCopies(id, extra);
                System.out.println("Copies updated.");
            }
            case "6" -> {
                System.out.print("Book ID: ");
                String id = SCANNER.nextLine().trim();
                bookService.deleteBook(id);
                System.out.println("Book deleted.");
            }
            default -> System.out.println("Invalid option.");
        }
    }

    // ---------------------- Transaction Management ----------------------

    private static void transactionMenu() throws LibraryException {
        System.out.println("\n-- Transaction Management --");
        System.out.println("1. Issue book");
        System.out.println("2. Return book");
        System.out.println("3. List all transactions");
        System.out.println("4. List transactions for a member");
        System.out.println("5. List overdue transactions");
        System.out.print("Choose an option: ");
        String choice = SCANNER.nextLine().trim();

        switch (choice) {
            case "1" -> {
                System.out.print("Book ID: ");
                String bookId = SCANNER.nextLine().trim();
                System.out.print("Member ID: ");
                String memberId = SCANNER.nextLine().trim();
                Transaction t = transactionService.issueBook(bookId, memberId);
                System.out.println("Issued: " + t);
            }
            case "2" -> {
                System.out.print("Transaction ID: ");
                String id = SCANNER.nextLine().trim();
                Transaction t = transactionService.returnBook(id);
                System.out.println("Returned: " + t);
                if (t.getFineAmount() > 0) {
                    System.out.println("Fine due: Rs." + t.getFineAmount());
                }
            }
            case "3" -> transactionService.listAll().forEach(System.out::println);
            case "4" -> {
                System.out.print("Member ID: ");
                String memberId = SCANNER.nextLine().trim();
                List<Transaction> txns = transactionService.listByMember(memberId);
                if (txns.isEmpty()) System.out.println("No transactions for this member.");
                txns.forEach(System.out::println);
            }
            case "5" -> {
                List<Transaction> overdue = transactionService.listOverdue();
                if (overdue.isEmpty()) System.out.println("No overdue transactions.");
                overdue.forEach(System.out::println);
            }
            default -> System.out.println("Invalid option.");
        }
    }

    // ---------------------- Reports ----------------------

    private static void reportsMenu() {
        System.out.println("\n-- Reports --");
        System.out.println(reportService.summary());
        System.out.println("Top borrowed books:");
        List<Map.Entry<String, Long>> top = reportService.topBorrowedBooks(5);
        if (top.isEmpty()) {
            System.out.println("  (no transactions yet)");
        } else {
            for (Map.Entry<String, Long> entry : top) {
                System.out.println("  " + entry.getKey() + " -> " + entry.getValue() + " issue(s)");
            }
        }
    }
}
