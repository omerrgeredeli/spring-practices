package library2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Başlangıçta verileri dosyadan yükle
        library.loadDataFromFile();

        // Örnek kütüphaneci
        Librarian librarian = new Librarian(1, "Admin", "admin@library.com");

        int choice = -1;
        while (choice != 7) {
            System.out.println("\n=== LIBRARY MENU ===");
            System.out.println("1. Kitap ekle");
            System.out.println("2. Kitap ödünç al");
            System.out.println("3. Kitap iade");
            System.out.println("4. Kitapları listele");
            System.out.println("5. Kategoriye göre filtrele");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminiz: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> {
                        System.out.print("ID: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.print("Author: ");
                        String author = scanner.nextLine();
                        System.out.print("Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Published Year: ");
                        int year = Integer.parseInt(scanner.nextLine());
                        System.out.print("Category: ");
                        String category = scanner.nextLine();

                        Book book = new Book(id, author, title, year, category);
                        librarian.addBook(book, library.getBooks());
                    }

                    case 2 -> {
                        System.out.print("Öğrenci ID: ");
                        int studentId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Öğrenci Adı: ");
                        String studentName = scanner.nextLine();
                        System.out.print("Öğrenci Email: ");
                        String studentEmail = scanner.nextLine();

                        Student student = new Student(studentId, studentName, studentEmail);
                        if (!library.getUsers().contains(student)) {
                            library.getUsers().add(student);
                        }

                        System.out.print("Ödünç alınacak kitap ID: ");
                        int bookId = Integer.parseInt(scanner.nextLine());
                        Book book = library.getBooks().stream()
                                .filter(b -> b.getId() == bookId)
                                .findFirst()
                                .orElse(null);

                        try {
                            if (book == null) throw new BookNotAvailableException("Kitap bulunamadı!");
                            student.borrowBook(book);
                            library.borrowBook(student, book);
                        } catch (BookNotAvailableException e) {
                            System.out.println("Hata: " + e.getMessage());
                        }
                    }

                    case 3 -> {
                        System.out.print("Öğrenci ID: ");
                        int studentId = Integer.parseInt(scanner.nextLine());
                        Student student = library.getUsers().stream()
                                .filter(u -> u instanceof Student s && s.getId() == studentId)
                                .map(u -> (Student) u)
                                .findFirst()
                                .orElse(null);
                        if (student == null) {
                            System.out.println("Öğrenci bulunamadı!");
                            break;
                        }

                        System.out.print("İade edilecek kitap ID: ");
                        int bookId = Integer.parseInt(scanner.nextLine());
                        Book book = library.getBooks().stream()
                                .filter(b -> b.getId() == bookId)
                                .findFirst()
                                .orElse(null);

                        if (book == null) {
                            System.out.println("Kitap bulunamadı!");
                            break;
                        }

                        student.returnBook(book);
                        library.returnBook(student, book);
                    }

                    case 4 -> {
                        System.out.println("=== Tüm Kitaplar ===");
                        library.getBooks().forEach(System.out::println);
                    }

                    case 5 -> {
                        System.out.print("Filtrelenecek kategori: ");
                        String category = scanner.nextLine();
                        List<Book> filtered = library.filterBooksByCategory(category);
                        if (filtered.isEmpty()) {
                            System.out.println("Bu kategoride kitap bulunamadı!");
                        } else {
                            filtered.forEach(System.out::println);
                        }
                    }

                    case 6 -> {
                        System.out.println("Çıkış yapılıyor...");
                        library.saveDataToFile();
                        System.exit(0);
                    }

                    default -> System.out.println("Geçersiz seçim!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Lütfen geçerli bir sayı girin!");
            }
        }

        scanner.close();
    }
}
