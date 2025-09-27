package src;

public class Student extends Person implements Comparable<Student>{
    private double gpa;

    public Student(int id, String name, double gpa){
        super(id, name);
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String getInfo(){
        return "Student: " + getName() + ", GPA: " + gpa;
    }

    @Override
    public int compareTo(Student other){
        return Double.compare(this.gpa, other.gpa);
    }
}
