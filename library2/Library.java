package library2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Library{
    private Set<Book> books = new HashSet<>();
    private List<Person> users = new ArrayList<>();
    private Map<Student, List<Book>> borrowRecords = new HashMap<>();
    private Queue<String> transactionQueue = new LinkedList<>();

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public List<Person> getUsers() {
        return users;
    }

    public void setUsers(List<Person> users) {
        this.users = users;
    }

    public Map<Student, List<Book>> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(Map<Student, List<Book>> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    public Queue<String> getTransactionQueue() {
        return transactionQueue;
    }

    public void setTransactionQueue(Queue<String> transactionQueue) {
        this.transactionQueue = transactionQueue;
    }

    public void addBook(Book book){
        books.add(book);
        transactionQueue.add("Kitap eklendi: " + book.getTitle());
    }

    public void removeBook(Book book){
        if (books.remove(book)) {
            transactionQueue.add("Kitap silindi: " + book.getTitle());
        } else {
            System.out.println("Kitap bulunamadı: " + book.getTitle());
        }

    }

    public void borrowBook(Student student, Book book) throws BookNotAvailableException {
        if (!books.contains(book)) {
            throw new BookNotAvailableException("Kitap mevcut değil: " + book.getTitle());
        }
        borrowRecords.computeIfAbsent(student, k -> new ArrayList<>()).add(book);
        book.incrementBorrowCount();
        transactionQueue.add(student.getName() + " kitabı ödünç aldı: " + book.getTitle());
    }

    public void returnBook(Student student, Book book){
        List<Book> borrowed = borrowRecords.get(student);
        if (borrowed != null && borrowed.remove(book)) {
            transactionQueue.add(student.getName() + " kitabı iade etti: " + book.getTitle());
        } else {
            System.out.println("İade işlemi başarısız. Kitap kayıtlı değil.");
        }
    }

    public ArrayList<Book> listBooksByAuthor(String author){
        return books.stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Book> listBooksByYear(int year){
        return books.stream()
            .filter(b -> b.getPublishedYear() == year)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Book> mostBorrowedBooks(int topN){
        return books.stream()
                .sorted(Comparator.comparing(Book::getBorrowCount).reversed())
                .limit(topN)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void saveDataToFile(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("library_data.txt"))) {
            for (Book book : books) {
                writer.write(book.getId() + "," + book.getTitle() + "," +
                        book.getAuthor() + "," + book.getPublishedYear() + "," +
                        book.getCategory() + "," + book.getBorrowCount());
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println("Dosya kaydetme hatası: " + e.getMessage());
        }
    }

    public void loadDataFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader("library_data.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Book book = new Book(Integer.parseInt(parts[0]), parts[1], parts[2],
                            Integer.parseInt(parts[3]), parts[4]);
                    // borrowCount’ı da setle
                    book.setBorrowCount(Integer.parseInt(parts[5]));
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya yükleme hatası: " + e.getMessage());
        }
    }

    public ArrayList<Book> filterBooksByCategory(String category){
        return books.stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
