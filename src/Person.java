package src;

public abstract class Person {
    public int id;//PersonID
    public String name;//Person name

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public abstract void roleDescription();

    public String getInfo(){
        String s = "ID " + id + " ,Name " + name;
        return s;
    }

}
