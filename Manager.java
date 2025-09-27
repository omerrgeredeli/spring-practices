public class Manager extends Employee implements Payable{
    private int teamSize;

    Manager(int id, String name, double salary) {
        super(id, name, salary);
    }

    public Manager(int id, String name, double salary, int teamSize) {
        super(id, name, salary);
        this.teamSize = teamSize;
    }

    @Override
    public void work(){
        System.out.println("Manager " + name + " is managing a team of " + teamSize + " people");
    }

    @Override
    public double calculateBonus() {
        return salary * 0.3;
    }
}
