public class Developer extends Employee implements Payable{
    private String programmingLanguage;

    Developer(int id, String name, double salary) {
        super(id, name, salary);
    }

    public Developer(int id, String name, double salary, String programmingLanguage) {
        super(id, name, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public void work(){
        System.out.println("Developer " + name + " is coding in " + programmingLanguage);
    }

    @Override
    public double calculateBonus() {
        return salary * 0.2;
    }
}
