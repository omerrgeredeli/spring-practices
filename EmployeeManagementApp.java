import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementApp {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Developer(1, "Ahmet", 50000, "Java"));
        employees.add(new Developer(2, "Elif", 55000, "Python"));
        employees.add(new Manager(3, "Mehmet", 80000, 5));
        employees.add(new Intern(4, "Ayse", 20000, 6));

        System.out.println("=== WORK OUTPUT ===");
        for (Employee e : employees) {
            e.work();
        }

        System.out.println("\n=== BONUS OUTPUT ===");
        for (Employee e : employees) {
            if (e instanceof Payable) {
                Payable p = (Payable) e;
                System.out.println(e.name + " bonus: " + p.calculateBonus());
            }
        }

        System.out.println("\n=== EMPLOYEE DETAILS ===");
        for (Employee e : employees) {
            System.out.println(e.getDetails());
        }
    }
}
