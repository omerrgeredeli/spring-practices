package library.model;

import java.util.HashSet;

public class Librarian {
    private int id;
    private String name;

    public Librarian(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void addBook(Book b, HashSet<Book> allBooks){
        if (allBooks.add(b)) {
            System.out.println(name + " added book: " + b.getTitle());
        } else {
            System.out.println("Book already exists: " + b.getTitle());
        }
    }
    public void removeBook(Book b, HashSet<Book> allBooks){
        if (allBooks.remove(b)) {
            System.out.println(name + " removed book: " + b.getTitle());
        } else {
            System.out.println("Book not found: " + b.getTitle());
        }
    }
    public String getInfo() {
        return "Librarian ID: " + id + ", Name: " + name;
    }
}
