package library.app;

import library.model.Book;
import library.model.Student;
import library.model.Librarian;
import library.service.LibrarySystem;
import java.util.List;

public class LibraryManagementSystemApp {
    public static void main(String[] args) {
        // 1️⃣ LibrarySystem oluştur
        LibrarySystem library = new LibrarySystem();

        // 2️⃣ Kitaplar (HashSet + LinkedList + Queue için kullanılacak)
        Book b1 = new Book(1, "Clean Code", "Robert C. Martin", 2008);
        Book b2 = new Book(2, "Effective Java", "Joshua Bloch", 2018);
        Book b3 = new Book(3, "Design Patterns", "GoF", 1994);
        Book b4 = new Book(4, "Refactoring", "Martin Fowler", 2018);

        // 3️⃣ Öğrenciler (ArrayList + HashMap + TreeMap)
        Student s1 = new Student(101, "Ahmet", 3.5);
        Student s2 = new Student(102, "Ayşe", 3.8);
        Student s3 = new Student(103, "Mehmet", 2.9);
        Student s4 = new Student(104, "Elif", 3.9);

        // 4️⃣ Öğrencileri kaydet
        library.registerStudent(s1);
        library.registerStudent(s2);
        library.registerStudent(s3);
        library.registerStudent(s4);

        // 5️⃣ Öğrenciler kitap ödünç alıyor
        s1.borrowBook(b1);
        s2.borrowBook(b2);
        s2.borrowBook(b3);
        s3.borrowBook(b1);
        s4.borrowBook(b4);
        s2.returnBook(b3);

        // 6️⃣ Kütüphaneci kitap ekleyip çıkarıyor (HashSet)
        Librarian librarian = new Librarian(1, "Fatma");
        librarian.addBook(b1, library.getAllBooks());
        librarian.addBook(b2, library.getAllBooks());
        librarian.addBook(b3, library.getAllBooks());
        librarian.addBook(b4, library.getAllBooks());
        librarian.removeBook(b2, library.getAllBooks());

        // 7️⃣ GPA’ya göre sıralama (TreeMap)
        library.rankStudentByGpa();

        // 9️⃣ Kitap istek kuyruğu (Queue)
        library.addBookRequest(b2);
        library.addBookRequest(b3);
        library.addBookRequest(b1);
        library.processBookRequest();
        library.processBookRequest();
        library.processBookRequest();
        library.processBookRequest(); // boş kuyruğu test

        // Öğrenciler ve kütüphaneci ayrı listelerde
        List<Student> studentList = List.of(s1, s2, s3, s4);
        List<Librarian> librarianList = List.of(librarian);

        System.out.println("\n=== Polymorphism Info (instanceof olmadan) ===");

    // Öğrenciler
        for (Student s : studentList) {
            System.out.println(s.getInfo() + " | Borrowed: " + s.getBorrowedBooks());
        }

    // Kütüphaneciler
        for (Librarian l : librarianList) {
            System.out.println(l.getInfo());
        }


        // 1️⃣1️⃣ HashMap ile öğrenci ID bazlı erişim
        System.out.println("\n=== Find Student by ID ===");
        System.out.println("Student 102: " + library.findStudentById(102).getInfo());
    }
}
