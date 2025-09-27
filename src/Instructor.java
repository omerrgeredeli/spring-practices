package src;

public class Instructor extends Person implements Payable{
    private double salary;

    public Instructor(int id, String name, double salary){
        super(id,name);
        this.salary = salary;
    }
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getInfo(){
        return "Instructor " + getName() + " ,Salary " + salary;
    }

    @Override
    public double calculateBonus(){
        return  salary * 0.1;
    }
}
