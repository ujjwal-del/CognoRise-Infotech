import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private final String title;
    private final String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void checkOut() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("The book '" + title + "' has been checked out.");
        } else {
            System.out.println("The book '" + title + "' is already checked out.");
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println("The book '" + title + "' has been returned.");
        } else {
            System.out.println("The book '" + title + "' is already available.");
        }
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Status: " + (isAvailable ? "Available" : "Checked out"));
    }
}

class LibraryCatalog {
    private final ArrayList<Book> books;

    public LibraryCatalog() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book '" + book.getTitle() + "' by " + book.getAuthor() + " has been added.");
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.displayBookInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found with the title: " + title);
        }
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                book.displayBookInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No book found by the author: " + author);
        }
    }

    public void checkOutBook(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.checkOut();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No book found with the title: " + title);
        }
    }

    public void returnBook(String title) {
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No book found with the title: " + title);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();
        try (Scanner scanner = new Scanner(System.in)) {
            catalog.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));
            catalog.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
            catalog.addBook(new Book("1984", "George Orwell"));
            catalog.addBook(new Book("Pride and Prejudice", "Jane Austen"));
            catalog.addBook(new Book("The Catcher in the Rye", "J.D. Salinger"));
            catalog.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien"));
            catalog.addBook(new Book("The Hunger Games", "Suzanne Collins"));
            catalog.addBook(new Book("The Handmaid's Tale", "Margaret Atwood"));
            catalog.addBook(new Book("The Nightingale", "Kristin Hannah"));
            catalog.addBook(new Book("The Hate U Give", "Angie Thomas"));

            int choice;
            do {
                System.out.println("\nLibrary Management System:");
                System.out.println("1. Add a Book");
                System.out.println("2. Search by Title");
                System.out.println("3. Search by Author");
                System.out.println("4. Check Out a Book");
                System.out.println("5. Return a Book");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter book author: ");
                        String author = scanner.nextLine();
                        catalog.addBook(new Book(title, author));
                    }
                    case 2 -> {
                        System.out.print("Enter book title to search: ");
                        String searchTitle = scanner.nextLine();
                        catalog.searchByTitle(searchTitle);
                    }
                    case 3 -> {
                        System.out.print("Enter author name to search: ");
                        String searchAuthor = scanner.nextLine();
                        catalog.searchByAuthor(searchAuthor);
                    }
                    case 4 -> {
                        System.out.print("Enter book title to check out: ");
                        String checkOutTitle = scanner.nextLine();
                        catalog.checkOutBook(checkOutTitle);
                    }
                    case 5 -> {
                        System.out.print("Enter book title to return: ");
                        String returnTitle = scanner.nextLine();
                        catalog.returnBook(returnTitle);
                    }
                    case 6 -> System.out.println("Exiting Library Management System.");
                    default -> System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 6);
        }
    }
}