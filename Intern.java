public class Intern extends Employee implements Payable{
    private int durationInMonths;

    Intern(int id, String name, double salary) {
        super(id, name, salary);
    }

    public Intern(int id, String name, double salary, int durationInMonths) {
        super(id, name, salary); // Employee constructor'ını çağır
        this.durationInMonths = durationInMonths; // ek alanı ata
    }

    @Override
    public void work(){
        System.out.println("Intern " + name + " is learnin for " + durationInMonths + " months");
    }

    @Override
    public double calculateBonus() {
        return 0;
    }
}
