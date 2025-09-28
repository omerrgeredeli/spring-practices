package library.model;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student>{
    private int id;
    private String name;
    private double gpa;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void borrowBook(Book b){
        if (!borrowedBooks.contains(b)) {
            borrowedBooks.add(b);
            System.out.println(name + " borrowed: " + b.getTitle());
        } else {
            System.out.println(name + " already borrowed: " + b.getTitle());
        }
    }
    public void returnBook(Book b) {
        if (borrowedBooks.contains(b)) {
            borrowedBooks.remove(b);
            System.out.println(name + " returned: " + b.getTitle());
        } else {
            System.out.println(name + " has not borrowed: " + b.getTitle());
        }
    }

    @Override
    public int compareTo(Student other){
        return Double.compare(this.gpa,other.gpa);
    }

    public String getInfo() {
        return "Student ID: " + id + ", Name: " + name + ", GPA: " + gpa;
    }

}
