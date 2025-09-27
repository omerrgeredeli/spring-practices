public abstract class Employee {
    public int id;
    public String name;
    public double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    public abstract void work();

    public String getDetails(){
        String s = "ID: " + id + ", Name: " + name + ", Salary: " + salary;
        return s;
    }
}
