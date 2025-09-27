package src;

public class Department {
    private String name;
    private String faculty;

    public Department(String name, String faculty){
        this.name = name;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department" + name + ", faculty='" + faculty;
    }
}
