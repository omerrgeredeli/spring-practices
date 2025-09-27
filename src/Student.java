package src;

public class Student extends Person {
    public int courseCount;

    public Student(int id, String name, int courseCount){
        super(id, name);
        this.courseCount = courseCount;
    }

    @Override
    public void roleDescription(){
        System.out.println("Student " + name + " is enrolled in " + courseCount + " courses");
    }
}
