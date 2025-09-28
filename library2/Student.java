package library2;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person implements Borrowable{
    private List<Book> borrowedBooks = new ArrayList<>();
    private int totalBorrowed;

    public Student(int id, String name, String email, List<Book> borrowedBooks, int totalBorrowed){
        super(id,name,email);
        this.borrowedBooks = borrowedBooks;
        this.totalBorrowed = totalBorrowed;
    }

    public Student(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void borrowBook(Book b){
        if (!borrowedBooks.contains(b)) {
            borrowedBooks.add(b);
            System.out.println(getName() + " borrowed: " + b.getTitle());
        } else {
            System.out.println(getName() + " already borrowed: " + b.getTitle());
        }
    }

    @Override
    public void returnBook(Book b){
        if (borrowedBooks.contains(b)) {
            borrowedBooks.remove(b);
            System.out.println(getName() + " returned: " + b.getTitle());
        } else {
            System.out.println(getName() + " has not borrowed: " + b.getTitle());
        }
    }
}
