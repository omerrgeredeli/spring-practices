package src;

public class Teacher extends Person implements Payable{
    public String subject;
    public double salary;

    public Teacher(int id, String name, String subject, double salary){
        super(id,name);
        this.subject =subject;
        this.salary =salary;
    }

    @Override
    public void roleDescription(){
        System.out.println("Teacher " + name + " teaches subject " + subject);
    }


    @Override
    public double calculatePayment() {
        return salary * 1.1;
    }
}
