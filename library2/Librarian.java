package library2;

import java.util.HashSet;
import java.util.Set;

public class Librarian extends Person{
    public Librarian(int id, String name, String email){
        super(id,name,email);
    }

    public void addBook(Book b, Set<Book> allBooks){
        if (allBooks.add(b)) {
            System.out.println(getName() + " added book: " + b.getTitle());
        } else {
            System.out.println("Book already exists: " + b.getTitle());
        }
    }
    public void removeBook(Book b, HashSet<Book> allBooks){
        if (allBooks.remove(b)) {
            System.out.println(getName() + " removed book: " + b.getTitle());
        } else {
            System.out.println("Book not found: " + b.getTitle());
        }
    }
    public void generateReport(Library library){
        System.out.println("=== Kütüphane Raporu ===");
        System.out.println("Toplam Kitap: " + library.getBooks().size());

        library.getBooks().forEach(book -> {
            System.out.println(
                    "ID: " + book.getId() +
                            ", Title: " + book.getTitle() +
                            ", Author: " + book.getAuthor() +
                            ", Year: " + book.getPublishedYear() +
                            ", Borrow Count: " + book.getBorrowCount()
            );
        });

        System.out.println("========================");
    }
}
