package library.service;

import library.model.Student;
import library.model.Book;

import java.util.*;

public class LibrarySystem {
    private ArrayList<Student> students = new ArrayList<>();
    private LinkedList<Book> readingQueue = new LinkedList<>();
    private HashSet<Book> allBooks = new HashSet<>();
    private HashMap<Integer,Student> studentMap =  new HashMap<>();
    private TreeMap<Double,Student> studentRanking =  new TreeMap<>();
    private Queue<Book> requestBook =  new LinkedList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public LinkedList<Book> getReadingQueue() {
        return readingQueue;
    }

    public void setReadingQueue(LinkedList<Book> readingQueue) {
        this.readingQueue = readingQueue;
    }

    public HashSet<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(HashSet<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public HashMap<Integer, Student> getStudentMap() {
        return studentMap;
    }

    public void setStudentMap(HashMap<Integer, Student> studentMap) {
        this.studentMap = studentMap;
    }

    public TreeMap<Double, Student> getStudentRanking() {
        return studentRanking;
    }

    public void setStudentRanking(TreeMap<Double, Student> studentRanking) {
        this.studentRanking = studentRanking;
    }

    public Queue<Book> getRequestBook() {
        return requestBook;
    }

    public void setRequestBook(Queue<Book> requestBook) {
        this.requestBook = requestBook;
    }

    public void registerStudent(Student s){
        students.add(s);
        studentMap.put(s.getId(), s);
    }
    public Student findStudentById(int id){
        return studentMap.get(id);
    }
    public void rankStudentByGpa(){
        TreeMap<Double, Student> ranking = new TreeMap<>();
        for (Student s : students) {
            ranking.put(s.getGpa(), s);
        }

        System.out.println("=== Student Ranking by GPA ===");
        for (var entry : ranking.descendingMap().entrySet()) {
            System.out.println(entry.getValue().getName() + " - GPA: " + entry.getKey());
        }
    }
    public void addBookRequest(Book b){
        requestBook.add(b);
    }
    public void processBookRequest(){
        Book b = requestBook.poll();
        if (b != null) {
            System.out.println("Processing book request: " + b.getTitle());
        } else {
            System.out.println("No pending book requests.");
        }
    }

}

