package src;

public class Student extends Person {
    private int courseCount;

    public Student(int id, String name, int courseCount){
        super(id, name);
        this.courseCount = courseCount;
    }

    public int getCourseCount(){
        return courseCount;
    }
    public void setCourseCount(int courseCount){
        this.courseCount = courseCount;
    }

    @Override
    public void roleDescription(){
        System.out.println("Student " + getName() + " is enrolled in " + courseCount + " courses");
    }
}
