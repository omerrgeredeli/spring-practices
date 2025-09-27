package src;

public class TeachingAssistant extends Student implements Payable{
    public int hoursWorked;
    public double hourlyRate;

    public TeachingAssistant(int id, String name, int courseCount, int hoursWorked, double hourlyRate){
        super(id,name,courseCount);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void roleDescription(){
        System.out.println("TA "+ name + " assists in " + courseCount + " courses for "+ hoursWorked + " hours");
    }


    @Override
    public double calculatePayment() {
        return hoursWorked * hourlyRate;
    }
}
