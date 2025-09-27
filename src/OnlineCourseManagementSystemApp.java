package src;

import java.util.ArrayList;
import java.util.List;

public class OnlineCourseManagementSystemApp {
    public static void main(String[] args){
        List<Person> people = new ArrayList<>();

        people.add(new Teacher(1, "Omer", "Matematik", 100000));
        people.add(new Teacher(2, "Busra", "Turkce", 80000));
        people.add(new Student(10, "Mevlut",2));
        people.add(new Student(11, "Mehmet", 1));
        people.add(new TeachingAssistant(3,"Ahmet", 2, 10, 0.3));

        System.out.println("///ROLE DESCRIPTIONS");
        for(Person p: people){
            p.roleDescription();
        }

        System.out.println("\n=== PAYMENTS ===");
        for (Person peopleList : people) {
            if (peopleList instanceof Payable) {
                Payable p = (Payable) peopleList;
                System.out.println(peopleList.getName() + " payment: " + p.calculatePayment());
            }
        }

        System.out.println("\n===INFO ABOUT ALL PEOPLE");
        for(Person p: people){
             System.out.println(p.getInfo());
        }
    }
}
