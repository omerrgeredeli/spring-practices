package src;

public class Teacher extends Person implements Payable{
    private String subject;
    private double salary;

    public Teacher(int id, String name, String subject, double salary){
        super(id,name);
        this.subject =subject;
        this.salary =salary;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalary(double salary){
        this.salary = salary;
    }

    @Override
    public void roleDescription(){
        System.out.println("Teacher " + getName() + " teaches subject " + subject);
    }


    @Override
    public double calculatePayment() {
        return salary * 1.1;
    }
}
