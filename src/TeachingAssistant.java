package src;

public class TeachingAssistant extends Student implements Payable{
    private int hoursWorked;
    private double hourlyRate;

    public TeachingAssistant(int id, String name, int courseCount, int hoursWorked, double hourlyRate){
        super(id,name,courseCount);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }
    public int getHoursWorked(){
        return hoursWorked;
    }

    public void setHoursWorked(int hoursWorked){
        this.hoursWorked = hoursWorked;
    }

    public double getHourlyRate(){
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void roleDescription(){
        System.out.println("TA "+ getName() + " assists in " + getCourseCount() + " courses for "+ hoursWorked + " hours");
    }


    @Override
    public double calculatePayment() {
        return hoursWorked * hourlyRate;
    }
}
