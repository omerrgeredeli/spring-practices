package src;
import  java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Set;
import java.util.Map;


public class UniversityManagementSystemApp {
    public static void main(String[] args){
        // ===== ArrayList: Öğrenciler =====
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ayşe", 3.5));
        students.add(new Student(2, "Mehmet", 2.9));
        students.add(new Student(3, "Ali", 3.8));

        System.out.println("=== ArrayList - Students ===");
        for (Student s : students) {
            System.out.println(s.getInfo());
        }

        // Ortalama GPA
        double totalGpa = 0;
        for (Student s : students) totalGpa += s.getGpa();
        System.out.println("Average GPA: " + (totalGpa / students.size()));

        // ===== LinkedList: Ders kayıt sırası =====
        LinkedList<String> courseQueue = new LinkedList<>();
        courseQueue.add("CS101");
        courseQueue.add("MA101");
        courseQueue.add("PH101");
        System.out.println("\n=== LinkedList - Course Queue ===");
        System.out.println(courseQueue);
        courseQueue.removeFirst();
        System.out.println("After removeFirst(): " + courseQueue);

        // ===== HashSet: Kulüp üyeleri =====
        Set<String> aiClub = new HashSet<>();
        aiClub.add("Ayşe");
        aiClub.add("Ali");
        aiClub.add("Ayşe"); // duplicate
        System.out.println("\n=== HashSet - AI Club Members ===");
        System.out.println(aiClub);

        // ===== HashMap: CourseID -> Course =====
        Map<Integer, Course> courseMap = new HashMap<>();
        courseMap.put(101, new Course(101, "Computer Science", 4));
        courseMap.put(102, new Course(102, "Mathematics", 3));
        System.out.println("\n=== HashMap - Courses ===");
        System.out.println(courseMap.get(101));

        // ===== TreeMap: DepartmentName -> Department =====
        Map<String, Department> departments = new TreeMap<>();
        departments.put("CS", new Department("CS", "Engineering"));
        departments.put("MA", new Department("MA", "Science"));
        System.out.println("\n=== TreeMap - Departments ===");
        for (String key : departments.keySet()) {
            System.out.println(key + " -> " + departments.get(key));
        }

        // ===== Queue: Mezuniyet sırası =====
        Queue<Student> graduationQueue = new LinkedList<>();
        graduationQueue.add(students.get(0));
        graduationQueue.add(students.get(1));
        System.out.println("\n=== Queue - Graduation Queue ===");
        while (!graduationQueue.isEmpty()) {
            System.out.println(graduationQueue.poll().getInfo());
        }

        // ===== Payable: Instructor Bonus =====
        List<Payable> payables = new ArrayList<>();
        payables.add(new Instructor(1, "Ahmet", 15000));
        payables.add(new Instructor(2, "Zeynep", 18000));

        System.out.println("\n=== Payable - Instructor Bonuses ===");
        for (Payable p : payables) {
            System.out.println(((Instructor)p).getInfo() + " | Bonus: " + p.calculateBonus());
        }

        List<Person> people = new ArrayList<>();
        people.addAll(students);


        System.out.println("\n=== Polymorphism - Person Info ===");
        for (Person p : people) {
            System.out.println(p.getInfo());
        }
    }

}
